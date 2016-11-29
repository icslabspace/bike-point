(ns bike-point.web-client
  (:require [aleph.http :as http]
            [byte-streams :as bs]
            [clojure.data.json :as json]))

(def app-id "")
(def app-key "")
(def app-params {:app_id app-id :app_key app-key})

(defn- make-qparams [k v & kvs]
  {:query-params (apply assoc app-params k v kvs)})

(defn- make-http-req [url lat long r]
  (http/get url (make-qparams :lat lat
                              :long long
                              :radius r)))

(defn request-bikepoints [url lat long radius]
  (-> @(make-http-req url lat long radius)
      :body
      bs/to-string
      json/read-str))
