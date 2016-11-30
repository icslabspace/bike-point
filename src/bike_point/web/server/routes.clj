(ns bike-point.web.server.routes
  (:require [bike-point.web.server.resources :as r]
            [bidi.ring :as bring]
            [yada.yada :as yada]))

(def query-routes
  ["/"
   [
    ["query" (r/query-resource)]
    ["Leyton" (r/leyton-query 51.56 -0.013 4000 5)]
    [true (yada/as-resource nil)]]])
