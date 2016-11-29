(ns bike-point.locations
  (:require [bike-point.parser :refer [bp-from-json]]))

(defn output-html [json]
  [:table [:tr [:td (bp-from-json json)]]])
