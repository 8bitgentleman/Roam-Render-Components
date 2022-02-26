(ns quote-card-v6
  (:require
   [reagent.core :as r]
   [datascript.core :as d]
   [roam.datascript.reactive :as dr]
   [clojure.pprint :as pp]
   [clojure.string :as string]
   [roam.util :as u]
   [roam.block :as block]
   ))


(def app-data
     (r/atom {:book "Book/How to Take Smart Notes" 
              :author "Sönke Ahrens"
              :highlight "There is no point in having great tools if they don't fit together "
              :chapter ""
              :cover "https://firebasestorage.googleapis.com/v0/b/firescript-577a2.appspot.com/o/imgs%2Fapp%2FTest_onboard%2Fp7LKsdZRNR.jpg?alt=media&token=4e91252b-a694-485d-a065-4d09ccb2ca48"
              :uid "XqNLVenef"
              :parent ""}))


(def results
    @(dr/q '[:find (pull ?c [:block/string
                             :block/uid
                             :block/heading
                             {:block/children ...}]) 
              :where 
                [?refs :block/refs ?title]
                [?title :node/title "quotes"]
                [?refs :block/children ?c]])
    )
(defn getPageTitleByBlockUid [block-uid]
  @(dr/q '[:find (pull ?p [:node/title]) .
               :in $ ?uid
               :where
                      [?e :block/uid ?uid]
                      [?e :block/page ?p]]
          block-uid)
  )
(defn strToPage [string ]
  (+ "[[" string "]]")
  )
(defn strToRef [string ]
  (+ "((" string "))")
  )
(defn getBookTitle 
  [uid]
  (-> uid
   getPageTitleByBlockUid
	(get :node/title)
    ))

(defn getBookCover [page]
  (or (:block/string @(dr/q '[:find (pull ?c [:block/string
                     :block/uid
                     {:block/children ...}]) .
         	:in $ ?book
            :where
                [?p :node/title ?book]
                [?b :block/page ?p]
                [?b :block/uid ?u]
                [?b :block/string ?s]
                [(clojure.string/includes? ?s  "Cover::")]
                [?b :block/children ?c]
                ]
          page))
      "![](https://readwise-assets.s3.amazonaws.com/static/images/default-book-icon-4.11327a2af05a.png)")
  )
(defn getAuthorWriters [page]
  (string/split
    (or (or (:block/string @(dr/q '[:find (pull ?b [:block/string]) .
           	:in $ ?book
              :where
                  [?p :node/title ?book]
                  [?b :block/page ?p]
                  [?b :block/uid ?u]
                  [?b :block/string ?s]
                  [(clojure.string/includes? ?s  "Author::")]
                  ]
            page))
        (:block/string @(dr/q '[:find (pull ?b [:block/string]) .
           	:in $ ?book
              :where
                  [?p :node/title ?book]
                  [?b :block/page ?p]
                  [?b :block/uid ?u]
                  [?b :block/string ?s]
                  [(clojure.string/includes? ?s  "Writers::")]
                  ]
            page)))
        "Author::")
   "::")
  )

(defn flattenBookCoverUrl 
  [page]
  (->> page
   getBookCover
    (re-matches #"!\[.*\]\((.+)\)" )
    last
    ))

(defn flatten-block 
  "Flattens blocks children into a flat list"
  [acc block]
  (reduce flatten-block
          (conj acc (dissoc block :block/children))
          (:block/children block)))

(defn filter-heading
  [ks coll]
  (filter #(false? (contains? % ks)) coll))

(defn filter-attributes [filterstring coll]
  (filter #(false? (string/includes? 
            (:block/string %)
            (str filterstring)))
         coll)
          )

(defn filter-blocks [coll]
  (->> coll
    (filter-heading :block/heading )
    
    (filter-attributes "::")
    )
  )

(defn media-context[]
   [:div.media {:style{:display: "flex"}}
    [:img
     {:style {:height "66px"
              :position "relative"
              :float "left"
              :margin-right "10px"},
      :src (:cover @app-data)}]
    [:div
      [:div.is-flex
       [:span.highlight-title {:style{
                                    :font-weight "700"
    								:display: "inline"
								    :color "#1f1f1f"
                                    :word-break "break-word"}}
        (u/parse (strToPage (:book @app-data) ))]]
     [:div.highlight-sections 
      {:style{:color "#4a4a4a"}}
      (:chapter @app-data)]
     [:p.highlight-author 
      {:style{:color "#6b7888"}}
      (:author @app-data)]]]

  )

(defn card []
  [:div 
    [:div {:class "bp3-card bp3-elevation-2" 
           :style{ :overflow "hidden"}}    
     [media-context]
     [:p {:style{:font-size "1.25em"}}(u/parse (strToRef (:uid @app-data) )) ]
     ]
   ]
)
(defn get-highlight []
  (let [*quote (rand-nth(filter-blocks (flatten (map #(flatten-block [] % )(flatten results)))))] 
    (.log js/console *quote)
    
    (swap! app-data assoc-in [:cover] (flattenBookCoverUrl (getBookTitle (:block/uid *quote))))
    (swap! app-data assoc-in [:highlight] (:block/string *quote))
    (swap! app-data assoc-in [:uid] (:block/uid *quote))
    (swap! app-data assoc-in [:book] (getBookTitle (:block/uid *quote)))
    (swap! app-data assoc-in [:author] (getAuthorWriters (getBookTitle (:block/uid *quote))) )
    
    ))

(defn save-highlight []
 ;; (.log js/console parent-block)
  (block/create 
          {:location {:parent-uid (:parent @app-data)
                      :order 0}
           :block {:string (strToRef (:uid @app-data) )}}
   )
  )

(defn footer-button [text icon click-fn]
  "button"
  [:span {:class text}
   [:button {:class (+ "bp3-button
                     		bp3-small
                     		bp3-outlined
                     		bp3-icon-" icon)
                    :draggable false
                    :style {:margin "10px 5px"
                            ;;:float "right"
                            }
                    :on-click (fn [e](click-fn))}
           text]])

(defn main [{:keys [block-uid]} & args]
  ;;this is a dumb hack to avoid save-highlight triggering when args are passed
  (swap! app-data assoc-in [:parent] block-uid)
  	[:body.highlight-review
      [:div.highlight-card.highlight {:class "bp3-card bp3-elevation-2" 
             :style{:max-width "500px" 
                    :background-color "rgb(244,243,243)" 
                    :padding "0px"
                    }}
       [card (:book @app-data) (:highlight @app-data)]
       [footer-button "save" "export" save-highlight  ]
       [footer-button "another" "refresh" get-highlight]

     ]]
  )

