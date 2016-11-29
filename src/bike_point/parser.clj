(ns bike-point.parser)

;; TODO: mrewrite this
;; TODO: mrewrite this
;; TODO: mrewrite this
(defn- sort-by-distance [json]
  (sort-by :distance json)) ;; no required; json already sorted

(defn- pred-fn [m]
  (if (-> m :key (= "NbBikes"))
    (-> m :value read-string)
    nil))

(defn- get-nb-bikes [m]
  ;; (prn (->> m :additionalProperties
  ;;           (keep pred-fn)))

  (if-let [x (->> m :additionalProperties
                  (keep pred-fn)
                  first)]
    x
    0))

(defn- sanitize [place-entry nbikes]
  (assoc (select-keys place-entry [:commonName :distance])
         :nbikes nbikes))

(defn- keep-gz [place-entry]
  (let [nbikes (get-nb-bikes place-entry)]
    (if (> nbikes 0)
      (sanitize place-entry nbikes)
      [])))

(defn bp-from-json [n json]
  (loop [json (:places json)
         bikepoints []]
    (if (or (= n (count bikepoints)) (-> json seq nil?))
      bikepoints
      (recur (-> json rest)
             (conj bikepoints
                   (keep-gz (first json)))))) ;; TODO: make this lazy
)

;; TODO: make this lazy
;; TODO: make this lazy
;; TODO: make this lazy
