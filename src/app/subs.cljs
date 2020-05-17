(ns app.subs
  (:require
   [re-frame.core :as rf]))

(rf/reg-sub
 :files-uploaded?
 (fn [db]
   (not-empty (:raw-svg-files db))))
