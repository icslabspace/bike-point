(ns bike-point.test
  (:require [bike-point.web-client :as wcli]
            [bike-point.parser :as ps]))

(defn check-it-out []
  (let [res (wcli/test-run)
        places (->> res
                    :places
                    lazy-seq)
        rindex (rand-int (count places))
        results (ps/retrieve :key
                             "NbBikes"
                             :value
                             :nbikes
                             :additionalProperties
                             [:commonName :distance]
                             (nth places rindex))]
    results))

(defn pull-it-out []
  (let [res (wcli/test-run)
        places (->> res
                    :places
                    lazy-seq)
        results (ps/take-bikepoints 5 places)]
    results))
