(ns count-children-2
  (:require
   [reagent.core :as r]
   [datascript.core :as d]
   [roam.datascript.reactive :as dr]
   [clojure.pprint :as pp]))


(defn flatten-block
  "Flattens blocks children into a flat list"
  [acc block]
  (reduce flatten-block
          (conj acc (dissoc block :block/children))
          (:block/children block)))

(defn find-child-count
  "Counts all children blocks given a parent block uid"
  [block-uid]
  (- (count
      (flatten-block []
                     @(dr/q '[:find (pull ?e [:block/string {:block/children ...}]) .
                              :in $ ?uid
                              :where
                              [?e :block/uid ?uid]]
                            block-uid))) 1))

(defn main [{:keys [block-uid]} & args]
  ;; pass the component's own block-uid into the function
  [:span {:class "rm-child-count"}
   [:span (find-child-count block-uid)]])
