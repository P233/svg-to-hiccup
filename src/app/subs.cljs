(ns app.subs
  (:require
   [re-frame.core :as rf]))

(rf/reg-sub
 :svg-entries-list
 (fn [db]
   (:svg-entries-list db)))

(rf/reg-sub
 :svg-filess-count
 (fn [db]
   (count (:svg-entries-list db))))

(rf/reg-sub
 :duplicate-svg-filenames-list
 (fn [db]
   (:duplicate-svg-filenames-list db)))

(rf/reg-sub
 :duplicate-svg-files-count
 (fn [db]
   (count (:duplicate-svg-filenames-list db))))
