(ns app.events
  (:require
   [re-frame.core :as rf]
   [app.db :as db]))

(rf/reg-event-db
 :initialize-db
 (fn []
   db/default-db))

(rf/reg-event-db
 :upload-svg-files
 (fn [db [_ files]]
   (assoc db :raw-svg-files files)))

(rf/reg-event-db
 :clear-uploaded-svgs
 (fn [db]
   (assoc db :raw-svg-files [])))
