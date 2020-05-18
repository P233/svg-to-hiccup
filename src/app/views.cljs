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

(defn uploaded-msg [amount]
  [:p
   (str amount " " (if (> amount 1) "files" "file") " uploaded.")
   [:button
    {:on-click #(rf/dispatch [:clear-uploaded-svgs])}
    "clear"]])

(defn svgs-list []
  (map #(.-name %) @(rf/subscribe [:raw-svg-files])))

(defn page []
  [:<>
   (if @(rf/subscribe [:are-svgs-uploaded?])
     (let [amount @(rf/subscribe [:svgs-amount])]
       [:<>
        [uploaded-msg amount]
        [svgs-list]])
     [upload-button])])
