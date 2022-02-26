(ns shopping-sum-v2
  (:require
   [reagent.core :as r]
   [datascript.core :as d]
   [roam.datascript.reactive :as dr]
   [clojure.pprint :as pp]))



(defn flatten-block 
  "Flattens a blocks children into a flat list"
  [acc block]
  (reduce flatten-block
          (conj acc (dissoc block :block/children))
          (:block/children block)))

(defn find-child-refs
  "Returns all _refs for children blocks given a parent block uid"
  [block-uid]
  (flatten-block []
             @(dr/q '[:find (pull ?e [:block/string :block/uid {:block/children ...}]) .
                      :in $ ?uid
                      :where
                      [?e :block/uid ?uid]
                      ]
                    block-uid)))

(defn filter-content [content]
  "finds blocks that contain a price eg. $40. Not acutally used just useful"
  (map 
   	#(re-seq #".*\$(\d+)(?:\.\d+)?" (:block/string %))
    (filter #(re-seq #".*\$(\d+)(?:\.\d+)?" (:block/string %)) content))
	

  )

(defn sum-string [content]
  (->> content
    (re-seq #"\$\d+(?:\.\d+)?" )
    (map #(subs % 1) )
    (map int )
    (reduce +)
    )
  )

(defn main [{:keys [block-uid]} & args]
  [:div.rm-shopping-sum {:style{:margin-left "10px"}} "Total Spent: " 
   [:div.rm-shopping-sum-amount {:style{
                 :display "inline-block"
                
                }}
    "$"(reduce + (map #(sum-string (:block/string %)) (find-child-refs block-uid)))
    ]
     	
     ]
  )
