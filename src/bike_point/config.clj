(ns bike-point.config)

(def ^:dynamic *config*
  {:app_id "c0d3b98d"
   :app_key "3bc9804dbff606d36f61096a555c417b"
   :location [51.56 -0.013] ;; lat - long
   :radius 2000 ;; meters
   :base-url "https://api.tfl.gov.uk"
   :path "BikePoint"})

(defn app-params []
  (select-keys *config* [:app_id :app_key]))

(def query-url (str (:base-url *config*)
                    "/"
                    (:path *config*)))
