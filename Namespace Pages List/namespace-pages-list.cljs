(ns namespace-display-v1
  (:require
   [reagent.core :as r]
   [datascript.core :as d]
   [roam.datascript.reactive :as dr]
   [clojure.string :as string]
   [roam.util :as u]
   [clojure.pprint :as pp]))

(defn get-pages [namespace]
  "Gets all pages that start with the namespace"
  @(dr/q '[:find ?title:name 
                        :in $ ?namespace
                        :where [?page :node/title ?title:name]
                                [(clojure.string/starts-with? ?title:name ?namespace)]]
                  (str namespace "/")
  )
)
(defn namespace-page [page-name]
  "Create a roam link to the page"
    [:div (u/parse  (str "[[" page-name "]]")  )]        
)


(defn main [{:keys [block-uid]} & args]
    (let [radar (r/atom {
                         :pages (get-pages (string/join " " args))
                         })]
      [:div {:class "rm-namespace-list"
            :style {:display "inline-block"
                    :padding "0px 10px 10px 10px"
                    }
         }
        [:h4 "All "(string/join " " args) " Pages (" (count (:pages @radar)) ")"]
			
          (for [i (:pages @radar) ]
            (namespace-page (first i))
            )
         ]	
      )
  )
