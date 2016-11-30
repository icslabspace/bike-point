(ns bike-point.web.ui
  {:doc "Basic HTML table construct"}
  (:require [hiccup.core :as h]))

(defn- make-bpcell [v display]
  [:div {:style (str "display:" display)}
   v])

(defn- make-bpdiv [{:keys [commonName distance nbikes]}]
  (into
   [:div {:style "display:table-row"}]
   (map #(make-bpcell % "table-cell") [commonName distance nbikes])))

(defn- make-header [keys]
  (into
   [:div {:style "display:table-row"}]
   (map #(make-bpcell % "table-cell;font-weight:bold") keys)))

(defn output-html [bpentries]
  (h/html
   (into [:div {:style "display:table; width:100%"}
          (make-header ["Location name" "Distance" "Number of bikes"])]
         (conj (vec (map make-bpdiv bpentries))
               [:div {:style (str "position:absolute;"
                                  "bottom:0;"
                                  "width:90%")}
                [:a {:href "http://www.icslab.eu" :target "_blank"}
                 [:div {:style "text-align: center"}
                  ;; TODO: static resource here
                  "icslab.eu"]]]))))
