(ns bike-point.web-client
  (:require [aleph.http :as http]
            [byte-streams :as bs]
            [clojure.data.json :as json]
            [bike-point.config :as cfg]))

(defn- make-qparams [k v & kvs]
  {:query-params (apply assoc (cfg/app-params) k v kvs)})

(defn- make-http-req [url lat lon r]
  (http/get url (make-qparams :lat lat
                              :lon lon
                              :radius r)))

(defn request-bikepoints [url lat long radius]
  (-> @(make-http-req url lat long radius)
      :body
      bs/to-string
      (json/read-str :key-fn keyword)))

(defn test-run []
  (request-bikepoints cfg/query-url 51.56 -0.013 2150))
