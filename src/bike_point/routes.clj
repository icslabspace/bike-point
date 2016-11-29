(ns bike-point.routes
  (:require [bike-point.resources :as r]
            [yada.yada :as yada]))

(def query-routes
  ["/"
   [
    ["query" (r/query-handler)]
    ;["h" r/hdler]
    [true (yada/as-resource nil)]]])
