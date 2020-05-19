(ns app.views
  (:require
   [re-frame.core :as rf]
   [app.styles :as styles]
   [lib.core :refer [svg->hiccup]]))

(defn upload-button []
  [:label
   {:class styles/upload}
   [:span
    {:class styles/upload__button
     :type "button"}
    "Upload SVG files"]
   [:input
    {:class styles/upload__input
     :type "file"
     :accept ".svg"
     :multiple true
     :on-change #(rf/dispatch [:upload-svg-files (-> % .-target .-files)])}]])

(defn uploaded-msg [amount]
  [:p
   (str amount " " (if (> amount 1) "files" "file") " uploaded.")
   [:button
    {:on-click #(rf/dispatch [:clear-uploaded-svgs])}
    "clear"]])

(defn read-file [file]
  (let [reader (js/FileReader.)]
    (set! (.-onload reader)
          (fn [e]
            (-> e .-target .-result svg->hiccup)))
    (.readAsText reader file)))

(defn svgs-list []
  (map #(read-file %) @(rf/subscribe [:raw-svg-files])))

(defn page []
  [:<>
   (if @(rf/subscribe [:are-svgs-uploaded?])
     (let [amount @(rf/subscribe [:svgs-amount])]
       [:<>
        [uploaded-msg amount]
        [svgs-list]])
     [upload-button])])
