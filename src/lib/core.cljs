(ns lib.core
  (:require
   ["svgson" :refer [parse]]))

(defn json->hiccup [json]
  (js/console.log json))

(defn svg->hiccup [svg]
  (-> (parse svg)
      (.then #(json->hiccup %))))
