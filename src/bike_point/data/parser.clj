(ns bike-point.data.parser
  {:doc "This parses the BikePoint rest call\n
to a useful clj datastructure\n
for use in the server"}

  (:require [bike-point.utils :as boitils]))

(defn- select [k v m]
  (if (= (k m) v)
    m
    nil))

(defn search [k v vmaps]
  (some (partial select k v) vmaps))

(defn- build-entry [vk nk entry vkeys m];; e.g. :value :nbikes {...} [:cn :d]
  (merge (select-keys m vkeys) {nk (entry vk)}))

;; (bike-point.parser/retrieve :key "NbBikes" :value :nbikes :additionalProperties [:commonName :distance] (first places))

(defn- is-gz? [s]
  (if-let [s s]
    (< 0 (read-string s))
    false))

(defn retrieve
  ([m]
   (retrieve :key "NbBikes"
             :value :nbikes
             :additionalProperties
             [:commonName :distance]
             m))

  ([k v vk nk ek vkeys m]
   (retrieve k v vk is-gz? nk ek vkeys m))

  ([k v vk pred-vk nk ek vkeys m] ;; :key "NbBikes"
   (let [entry (search k v (ek m))]
     (if (-> entry vk pred-vk)
       (build-entry vk nk entry vkeys m)
       {}))))

(defn take-bikepoints

  ([n vplaces] (take-bikepoints retrieve n vplaces))

  ([retrieve-fn n vplaces]
   "n is the number of bikepoints to retrieve - having available bikes;
vplaces is a sorted-by-distance lazy-seq of maps each describing a bikepoint entity"
   (loop [rest-fn #(-> % rest lazy-seq)
          places (rest-fn vplaces)
          place (first vplaces)
          bikepoints [] ;; TODO: make it a smart lazy-seq for big n
          entry (retrieve-fn place)]
     (if (or (== n (count bikepoints))
             (-> places seq nil?))
       bikepoints
       (recur rest-fn
              (rest-fn places)
              (first places)
              (boitils/cond-conj entry #(-> % seq) bikepoints)
              (-> places first retrieve-fn)))
     )))
