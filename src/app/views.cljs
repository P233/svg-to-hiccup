(ns app.views
  (:require
   [clojure.string :as string]
   [re-frame.core :as rf]
   [hickory.core :as hickory]
   [app.icons :as Icons]
   [app.styles :as styles]))

(defn parse-svg-entry [name html]
  {:name name
   :html html
   :hiccup (->> html
                hickory/parse-fragment
                (map hickory/as-hiccup))})

(defn dispatch-svg-entry [file]
  (let [reader (js/FileReader.)]
    (set! (.-onload reader)
          #(let [name (.-name file)
                 html (-> % .-target .-result)]
             (rf/dispatch [:add-svg-entry (parse-svg-entry name html)])))
    (.readAsText reader file "UTF-8")))

(defn read-uploaded-files [e]
  (doseq [x (-> e .-target .-files)] (dispatch-svg-entry x)))

(defn get-hiccup [entry]
  (-> :hiccup
      entry
      str
      (string/replace #" \"\\n\"|^\(|\)$" "")
      (string/replace "viewbox" "viewBox")))

(defn UploadButton [large?]
  [:label
   {:class [styles/upload (when large? styles/upload--large)]}
   [Icons/upload
    {:class styles/upload__icon}]
   [:span
    {:class styles/upload__button}
    "Upload SVGs"]
   [:input
    {:class styles/upload__input
     :type "file"
     :accept ".svg"
     :multiple true
     :on-change read-uploaded-files}]])

(defn Controller [count]
  [:div
   {:class styles/controller}
   [:p
    {:class styles/controller__counter}
    (str count " SVG " (if (> count 1) "files" "file") " added.")]
   [UploadButton]
   [:button
    {:on-click #(rf/dispatch [:clear-svgs-list])}
    [Icons/trash]
    "Clear all SVGs"]])

(defn SVGsList [list]
  [:ul
   (for [entry list]
     ^{:key (:name entry)}
     [:li
      [:h2 (:name entry)]
      [:div {:dangerouslySetInnerHTML {:__html (:html entry)}}]
      [:pre>code (get-hiccup entry)]
      [:button
       {:on-click #(rf/dispatch [:remove-svg-entry entry])}
       [Icons/trash]
       "Remove"]])])

(defn DuplicatesMessage []
  [:div
   (str "File " (string/join ", " @(rf/subscribe [:duplicates-list])) " already added.")
   [:button {:on-click #(rf/dispatch [:clear-duplicates-list])}
    [Icons/cross]
    "Close"]])

(defn page []
  (let [svgs-count @(rf/subscribe [:svgs-count])
        duplicates-count @(rf/subscribe [:duplicates-count])]
    [:<>
     (when (> duplicates-count 0) [DuplicatesMessage])
     (if (> svgs-count 0)
       [:<>
        [Controller svgs-count]
        [SVGsList @(rf/subscribe [:svgs-list])]]
       [UploadButton true])]))
