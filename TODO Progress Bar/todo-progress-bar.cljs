(ns progress-bar-v6
  (:require
   [reagent.core :as r]
   [datascript.core :as d]
   [roam.datascript.reactive :as dr]
   [clojure.pprint :as pp]))

(defn find-db-id 
  "Finds the database ID given a page title"
  [pagename]
  
  @(dr/q '[:find (pull ?e [:db/id]) .
                      :in $ ?pagename
                      :where
                      [?e :node/title ?pagename]]
                    pagename)
  )

(defonce TODO-ID (atom (:db/id (find-db-id "TODO"))))
(defonce DONE-ID (atom (:db/id (find-db-id "DONE"))))

(defn flatten-block 
  "Flattens a blocks children into a flat list"
  [acc block]
  (reduce flatten-block
          (conj acc (dissoc block :block/children))
          (:block/children block)))

(defn flatten-refs 
  "Flattens blocks refs into a flat list"
  [ block]
  (->> block
     (map :block/refs)
      (flatten )
    	(map :db/id)
    	(frequencies))
  )

(defn find-child-refs 
  "Returns all _refs for children blocks given a parent block uid"
  [block-uid]
  (flatten-refs  (flatten-block []
             @(dr/q '[:find (pull ?e [:block/refs{:block/children ...}]) .
                      :in $ ?uid
                      :where
                      [?e :block/uid ?uid]]
                    block-uid)))
  )

(defn main [{:keys [block-uid]} & args]
  (let [tasks (r/atom {
                       :todo (get (find-child-refs block-uid) @TODO-ID 0)
                       :done (get (find-child-refs block-uid) @DONE-ID 0) 
                       } )]
   
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
