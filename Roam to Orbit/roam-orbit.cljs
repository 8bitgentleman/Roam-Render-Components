(ns orbit-v3
  (:require
   [reagent.core :as r]
   [datascript.core :as d]
   [roam.datascript.reactive :as dr]
   [clojure.string :as str]
   [clojure.pprint :as pp]))

(defonce db-name (nth (str/split js/location.hash #"\/") 2))

(defn child-string
  [block]
  (as-> block v
     (:block/children v)
      (nth v 0)
    (:block/string v)
      )
  )

(defn orbit-prompt [question answer]
  [:orbit-prompt {
                  :question question, 
                  :answer answer}] 
  )

(defn page-refs [page]
    (map :block/uid (:block/_refs @(dr/q '[:find (pull ?block [{:block/_refs ...}
                                                                :block/uid]) .
                                            :in $ ?pagename   
                                            :where
                                              [?block :node/title ?pagename]
                                              ]
                                    page)))
  )

(defn block-info [uid]
    @(dr/q '[:find (pull ?block [{:block/children ...}
                                  :block/string
                                  :block/children
                                  :block/uid]) .
            :in $ ?uid   
            :where
                    [?block :block/uid ?uid]
                    ]
      uid)
  )

(defn create-q-a [input]
  (array-map
    :question (str/replace (:block/string  (block-info input )) #"\[\[Orbit Prompt\]\]" "" )
    :answer (child-string  (block-info  input) )
   )
  )


(defn main [{:keys [block-uid]} & args]
  (let [items (map create-q-a (page-refs (nth args 0))  )]
   ; (print  (nth args 0)) 
    (print db-name)
    [:html 
       [:head 
            ;[:title "Obesity - Our World in Data"] 
            ;[:link {:rel "canonical", :href "https://ourworldindata.org/obesity"}] 
            [:meta {:property "og:title", :content (nth args 0)}] 
            [:meta {:property "og:site_name", :content db-name}]
        ] 
      [:div
      [:orbit-reviewarea { :color "green"} 
          (for [item items]
              ^{:key item} (orbit-prompt (:question item) (:answer item)))
      ]]]
    )
  
  )
