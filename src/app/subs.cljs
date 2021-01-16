(ns app.subs
  (:require
   [re-frame.core :as rf]))

(rf/reg-sub
 :svgs-list
 (fn [db]
   (:svgs-list db)))

(rf/reg-sub
 :svgs-count
 (fn [db]
   (count (:svgs-list db))))

(rf/reg-sub
 :duplicates-list
 (fn [db]
   (:duplicates-list db)))

(rf/reg-sub
 :duplicates-count
 (fn [db]
   (count (:duplicates-list db))))
