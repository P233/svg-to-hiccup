(ns app.core
  (:require [reagent.dom :as rdom]
            [re-frame.core :as rf]
            [app.views :as views]
            [app.events]
            [app.subs]))

(defn ^:dev/after-load mount []
  (rf/clear-subscription-cache!)
  (rdom/render [views/Page]
               (.getElementById js/document "app")))

(defn ^:export init []
  (rf/dispatch-sync [:initialize-db])
  (mount))
