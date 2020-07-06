(ns app.events
  (:require
   [re-frame.core :as rf]
   [hickory.core :as hickory]
   [app.db :as db]))

(rf/reg-event-db
 :initialize-db
 (fn []
   db/default-db))

(rf/reg-event-db
 :upload-svg
 (fn [db [_ name html]]
   (update-in db [:svg-list] conj {:name name
                                   :html html
                                   :hiccup (->> html
                                                hickory/parse-fragment
                                                (map hickory/as-hiccup))})))

(rf/reg-event-db
 :clear-svgs
 (fn [db]
   (assoc db :svg-list ())))
