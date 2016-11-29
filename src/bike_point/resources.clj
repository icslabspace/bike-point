(ns bike-point.resources
  (:require [yada.yada :refer [resource handler]]
            [bike-point.locations :refer [output-html]]))

(defn query-resource []
  (resource
   {:produces "text/plain"
    :response "CUCU"
    ;(output-html "TODO:this-shoud-be-a-call-to-bikepoint-rest-API")
    }))

(defn query-resource []
  (resource
   {:methods
    {:get
     {:parameters {:query {:lat Double :long Double :radius Double}}
      :produces "text/plain"
      :response "CUCU"
                                        ;(output-html "TODO:this-shoud-be-a-call-to-bikepoint-rest-API")
      }}}))

(def query-handler (handler query-resource))


;; TODO: clear below when done
(defn say-hello [ctx]
  (str "Hello " "Alex!\n"))

(def hello-parameters-resource
  (resource
   {:methods
    {:get
     {:produces "text/plain"
      :response say-hello}}}))

(def hdler (handler hello-parameters-resource))
