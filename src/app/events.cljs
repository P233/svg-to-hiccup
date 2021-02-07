(ns app.events
  (:require
   [re-frame.core :as rf]
   [app.db :as db]))

(rf/reg-event-db
 :initialize-db
 (fn []
   db/default-db))

(rf/reg-event-db
 :add-svg-entry
 (fn [db [_ entry]]
   (if (some #(= (:filename %) (:filename entry)) (:svg-entries-list db))
     (update-in db [:duplicate-svg-files-list] conj (:filename entry))
     (update-in db [:svg-entries-list] conj entry))))

(rf/reg-event-db
 :remove-svg-entry
 (fn [db [_ entry]]
   (update-in db [:svg-entries-list] (fn [list]
                                       (remove #(= % entry) list)))))

(rf/reg-event-db
 :clear-svg-entries-list
 (fn [db]
   (assoc db :svg-entries-list ())))

(rf/reg-event-db
 :clear-duplicate-svg-files-list
 (fn [db]
   (assoc db :duplicate-svg-files-list ())))
