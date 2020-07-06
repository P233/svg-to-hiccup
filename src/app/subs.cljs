(ns app.subs
  (:require
   [re-frame.core :as rf]))

(rf/reg-sub
 :svg-list
 (fn [db]
   (:svg-list db)))

(rf/reg-sub
 :svg-uploaded?
 (fn [db]
   (not-empty (:svg-list db))))

(rf/reg-sub
 :svg-count
 (fn [db]
   (count (:svg-list db))))
