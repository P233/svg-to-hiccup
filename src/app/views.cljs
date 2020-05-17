(ns app.views
  (:require
   [re-frame.core :as rf]
   [app.styles :as styles]))

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

(defn page []
  [:<>
   [upload-button]])
