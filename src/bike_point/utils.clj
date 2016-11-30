(ns bike-point.utils)

(defn cond-conj [e pred coll]
  (if (pred e)
    (conj coll e)
    coll))
