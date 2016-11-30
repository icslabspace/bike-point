(ns bike-point.main
  (:require [bike-point.web.server :refer [start is-stopped?]]))

(defn -main [& args]
  (println "Starting server...")
  (start)
  (println "Server started on localhost:3000")
  (loop [is-stopped (is-stopped?)]
    (if is-stopped
      "Bye Bye!"
      (recur (do (Thread/sleep 30000)
                 (is-stopped?))))))
