(ns bike-point.web-server
  (:require [yada.yada :refer [listener as-resource resource]]
            [bike-point.routes :refer [query-routes]]))

(def svr
  (listener query-routes
   ;; ["/"
   ;;  [
   ;;   ["hello" (as-resource "Hello World!")]
   ;;   ["test" (resource {:produces "text/plain"
   ;;                      :response "This is a test!"})]
   ;;   [true (as-resource nil)]]]
   {:port 3000}))
