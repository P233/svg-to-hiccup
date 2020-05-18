(ns app.subs
  (:require
   [re-frame.core :as rf]))

(rf/reg-sub
 :raw-svg-files
 (fn [db]
   (:raw-svg-files db)))

(rf/reg-sub
 :are-svgs-uploaded?
 (fn [db]
   (not-empty (:raw-svg-files db))))

(rf/reg-sub
 :svgs-amount
 (fn [db]
   (.-length (:raw-svg-files db))))
