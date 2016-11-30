(ns bike-point.web.server
  (:require [yada.yada :refer [listener]]
            [bike-point.web.server.routes :refer [query-routes]]))

(def server (atom nil))

(defn is-stopped? []
  (-> server deref nil?))

(defn is-running? []
  (not (is-stopped?)))

(defn start []
  (when (is-stopped?)
    (reset! server (listener query-routes {:port 3000}))))

(defn stop []
  (when (is-running?)
    ((:close @server))))
