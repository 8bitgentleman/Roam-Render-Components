(ns progress-bar-v10
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
             @(dr/q '[:find (pull ?e [:block/refs{:block/children ...}]) .
                      :in $ ?uid
                      :where
                      [?e :block/uid ?uid]]
                    block-uid)))

(defn id-title 
  "Gets a page's title from its db id"
  [id]
  (:node/title @(dr/pull '[:node/title] id))
  )

(defn info-from-id [id]
  	(or (:node/title @(dr/pull '[:node/title] id))
      (map
       id-title 
       (map 
       		:db/id
       		(:block/refs @(dr/pull '[:block/refs] id))
       ))
      )
  )

(defn count-occurrences 
  "Counts the occurances of a string in a list"
  [s slist]
  (->> slist
       flatten
       (filter #{s})
       count))


(defn recurse-search
  "Recursivly search through a block's children for all pages referenced"
  [block-uid]
  (->> block-uid
       (find-child-refs)
       (map :block/refs)
       (flatten)
       (map :db/id)
       (map  info-from-id)
       (flatten)))


(defn main [{:keys [block-uid]} & args]
  (let [tasks (r/atom {;; don't love that I do this search twice
                       :todo (count-occurrences "TODO" (recurse-search block-uid))
                       :done (count-occurrences "DONE" (recurse-search block-uid))} )]
    
      [:div
             [:div {:style {:display "flex"
                                 :align-items "center"}
                         }
                   [:span [:progress {
                      :id "file"
                      :name "percent-done"
                      :value (:done @tasks)
                      :max (+ (:todo @tasks) (:done @tasks))
                      :style{

                             :margin-left "10px"
                             :margin-right "10px"
                             }}]
                    ]
                    [:span [:div  (str (:done @tasks)  "/"
                                      (+ 
                                        (:done @tasks)
                                        (:todo @tasks))
                                    " Done"
                                    )]]

                   ]
           ]
  ))
;

