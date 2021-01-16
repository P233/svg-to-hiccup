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
   (if (some #(= (:name %) (:name entry)) (:svgs-list db))
     (update-in db [:duplicates-list] conj (:name entry))
     (update-in db [:svgs-list] conj entry))))

(rf/reg-event-db
 :remove-svg-entry
 (fn [db [_ entry]]
   (update-in db [:svgs-list] (fn [list]
                                (remove #(= % entry) list)))))

(rf/reg-event-db
 :clear-svgs-list
 (fn [db]
   (assoc db :svgs-list ())))

(rf/reg-event-db
 :clear-duplicates-list
 (fn [db]
   (assoc db :duplicates-list ())))
