(ns bike-point.web.server.resources
  {:doc "Yada resources used to make-up routes to be dispatched by server"}
  (:require [yada.yada :refer [yada resource as-resource handler]]
            [bidi.ring :refer [redirect]]
            [bike-point.web.client :as client]
            [bike-point.config :as cfg]
            [bike-point.web.ui :refer [output-html]]
            [bike-point.data.parser :refer [take-bikepoints]]))

(defn request-bikepoints [lat lon radius n]
  (let [res (client/request-bikepoints cfg/query-url lat lon radius)
        places (->> res
                    :places
                    lazy-seq)]
    (take-bikepoints n places)))

(defn display-bikepoints [ctx]
  (let [params (get-in ctx [:parameters :query])
        lat (:lat params)
        lon (:lon params)
        radius (:radius params)
        n (:noob params)]
    (output-html (request-bikepoints lat lon radius n))))

(defn- make-access-control [user pass]
  {:access-control
   {:scheme "Basic"
    :verify (fn [[u p]]
              (when (= [u p] [user pass])
                {:user user
                 :roles #{"secret/view"}}))
    :authorization {:methods {:get "secret/view"}}}})

(defn- make-methods [params-map response-fn]
  {:methods
   {:get
    {:parameters {:query params-map}
     :produces "text/html"
     :response response-fn}}})

(defn query-resource []
  (yada
   (resource
    (merge (into {} (as-resource "SECRET!"))
           (merge {:id :resources/query}
                  (make-access-control "icslab" "www.icslab.eu")
                  (make-methods {:lat Double :lon Double :radius Double :noob Double}
                                display-bikepoints))))))

(defn leyton-query [lat lon radius n]
  (let [url (str "/query?"
                 "lat=" lat
                 "&lon=" lon
                 "&radius=" radius
                 "&noob=" n)]
    (redirect url)))

;;TODO: write a more consistent approch to params usage; i.e. a single consistent API for creating/reading and extracting params like lat lon a.s.o
