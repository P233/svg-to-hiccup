(ns app.events
  (:require
   [re-frame.core :as rf]
   [akiroz.re-frame.storage :as rfs]))

(defn rfs-reg-event-fx [event-id handler]
  (rf/reg-event-fx
   event-id
   [(rfs/persist-db :svg-entries-store :svg-entries-list)]
   (fn [{:keys [db]} event-vec]
     (handler db event-vec))))

(defn rfs-reg-event-db [event-id handler]
  (rf/reg-event-fx
   event-id
   [(rfs/persist-db :svg-entries-store :svg-entries-list)]
   (fn [{:keys [db]} event-vec]
     {:db (handler db event-vec)})))

(rf/reg-event-db
 :initialize-db
 (fn []
   {:svg-entries-list (rfs/<-store :svg-entries-store)
    :duplicate-svg-filenames-list ()}))

(rfs-reg-event-fx
 :add-svg-entry
 (fn [db [_ entry]]
   (if (some #(= (:filename %) (:filename entry)) (:svg-entries-list db))
     {:fx [[:dispatch [:add-duplicate-svg-filename (:filename entry)]]]}
     {:db (update-in db [:svg-entries-list] conj entry)})))

(rfs-reg-event-db
 :remove-svg-entry
 (fn [db [_ entry]]
   (update-in db [:svg-entries-list] (fn [list]
                                       (remove #(= % entry) list)))))

(rfs-reg-event-db
 :clear-svg-entries-list
 (fn [db]
   (assoc db :svg-entries-list ())))

(rf/reg-event-db
 :add-duplicate-svg-filename
 (fn [db [_ filename]]
   (update-in db [:duplicate-svg-filenames-list] conj filename)))

(rf/reg-event-db
 :clear-duplicate-svg-filenames-list
 (fn [db]
   (assoc db :duplicate-svg-filenames-list ())))
