(ns app.views
  (:require
   [clojure.string :as string]
   [reagent.core :as r]
   [reagent.ratom :as ra]
   [re-frame.core :as rf]
   [hickory.core :as h]
   [app.icons :as Icons]
   [app.styles :as styles]))

(defn create-svg-entry [filename content]
  {:filename filename
   :html content
   :hiccup (-> content h/parse-fragment first h/as-hiccup)})

(defn dispatch-svg-entry [file]
  (let [reader (js/FileReader.)]
    (set! (.-onload reader)
          #(let [filename (.-name file)
                 content (-> % .-target .-result)]
             (rf/dispatch [:add-svg-entry (create-svg-entry filename content)])))
    (.readAsText reader file "UTF-8")))

(defn read-uploaded-files [e]
  (doseq [file (-> e .-target .-files)] (dispatch-svg-entry file)))

(defn stringify-hiccup [hiccup]
  (-> hiccup
      str
      (string/replace #"\s*\"\\n\"" "") ; remove ` "\n"`
      (string/replace "viewbox" "viewBox")))

(defn generate-literal-hiccup [entry]
  (-> entry :hiccup stringify-hiccup))

(defn generate-literal-svg-defn [entry]
  (as-> entry x
    (:hiccup x)
    (assoc-in x [1 :class] "__class__")
    (stringify-hiccup x)
    (string/replace x "\"__class__\"" "class") ; Unquoted "class"
    (str "(defn " (-> entry :filename (string/split ".") first) " [{:keys [class]}]\n\t" x ")")))

(defn generate-exported-file-content [namespace]
  (let [init-line (str "(ns " namespace ")")]
    (->> @(rf/subscribe [:svg-entries-list])
         (map generate-literal-svg-defn)
         (concat [init-line])
         (string/join "\n\n"))))

(defn download-exported-file [namespace filename]
  (let [blob (js/Blob. [(generate-exported-file-content namespace)] {:type "text/plain"})
        link (js/document.createElement "a")]
    (set! (.-href link) (js/URL.createObjectURL blob))
    (.setAttribute link "download" filename)
    (.appendChild js/document.body link)
    (.click link)
    (.removeChild js/document.body link)))

(defn UploadButton [large?]
  [:label
   {:class [styles/upload (when large? styles/upload--large)]}
   [Icons/upload
    {:class styles/upload__icon}]
   [:span
    {:class styles/upload__button}
    "Upload SVGs"]
   [:input
    {:type "file"
     :accept ".svg"
     :multiple true
     :on-change read-uploaded-files
     :class styles/upload__input}]])

(defn DownloadDropdown []
  (let [open? (r/atom false)
        namespace (r/atom "app.icons")
        exported-filename (ra/make-reaction #(-> @namespace (string/split ".") last (str ".cljs")))]
    (fn []
      [:div
       {:class styles/dropdown}
       [:button
        {:on-click #(swap! open? not)
         :class styles/dropdown__trigger}
        "Trigger"]
       (when @open?
         [:<>
          [:div
           {:class styles/dropdown__menu}
           [:input
            {:value @namespace
             :on-change #(reset! namespace (-> % .-target .-value))
             :class styles/dropdown__input}]
           [:button
            {:on-click #(download-exported-file @namespace @exported-filename)
             :class styles/dropdown__button}
            (str "Download " @exported-filename)]]
          [:div
           {:on-click #(swap! open? not)
            :class styles/dropdown__mask}]])])))

(defn Controller [count]
  [:div
   {:class styles/controller}
   [:p
    {:class styles/controller__counter}
    (str count " SVG " (if (> count 1) "files" "file") " added.")]
   [UploadButton]
   [DownloadDropdown]
   [:button
    {:on-click #(rf/dispatch [:clear-svg-entries-list])}
    [Icons/trash]
    "Clear all SVGs"]])

(defn SVGsList [list]
  [:ul
   (for [entry list]
     ^{:key (:filename entry)}
     [:li
      [:h2 (:filename entry)]
      [:div {:dangerouslySetInnerHTML {:__html (:html entry)}}]
      [:pre>code (generate-literal-hiccup entry)]
      [:button
       {:on-click #(rf/dispatch [:remove-svg-entry entry])}
       [Icons/trash]
       "Remove"]])])

(defn DuplicatesMessage []
  [:div
   (str "File " (string/join ", " @(rf/subscribe [:duplicate-svg-files-list])) " already added.")
   [:button {:on-click #(rf/dispatch [:clear-duplicate-svg-files-list])}
    [Icons/cross]
    "Close"]])

(defn Page []
  (let [svgs-count @(rf/subscribe [:svg-filess-count])
        duplicates-count @(rf/subscribe [:duplicate-svg-files-count])]
    [:<>
     (when (> duplicates-count 0) [DuplicatesMessage])
     (if (> svgs-count 0)
       [:<>
        [Controller svgs-count]
        [SVGsList @(rf/subscribe [:svg-entries-list])]]
       [UploadButton true])]))
