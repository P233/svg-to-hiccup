(ns app.views
  (:require
   [clojure.string :as string]
   [re-frame.core :as rf]
   [app.styles :as styles]))

(defn read-file [file]
  (let [reader (js/FileReader.)]
    (set! (.-onload reader)
          #(rf/dispatch [:upload-svg
                         (.-name file)
                         (-> % .-target .-result)]))
    (.readAsText reader file "UTF-8")))

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
     :on-change #(doseq [x (-> % .-target .-files)] (read-file x))}]])

(defn uploaded-msg [amount]
  [:p
   (str amount " " (if (> amount 1) "files" "file") " uploaded.")
   [:button
    {:on-click #(rf/dispatch [:clear-svgs])}
    "clear"]])

(defn svg-list [list]
  [:ul
   (for [svg list]
     ^{:key (:name svg)}
     [:li
      [:h2 (:name svg)]
      [:div {:dangerouslySetInnerHTML
             {:__html (:html svg)}}]
      [:div (-> :hiccup svg str (string/replace #" \"\\n\"|^\(|\)$" ""))]])])

(defn page []
  [:<>
   (if @(rf/subscribe [:svg-uploaded?])
     [:<>
      [uploaded-msg @(rf/subscribe [:svg-count])]]
     [upload-button])
   [svg-list @(rf/subscribe [:svg-list])]])
