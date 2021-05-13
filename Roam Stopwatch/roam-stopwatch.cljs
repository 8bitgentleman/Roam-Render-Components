(ns roam-stopwatch-v2
  (:require
   [reagent.core :as r]
   [datascript.core :as d]
   [roam.datascript.reactive :as dr]
   [roam.block :as block]))


(def increase-time #(let [elapsed-time (inc (:elapsed-time %))] (assoc % :elapsed-time elapsed-time)))

(defn seconds-to-time
 "Converts stopwatch time in seconds to hours:minutes:seconds"
 [secs]
 (let [d (js/Date. (* secs 1000))]
   {:hours   (.getUTCHours d)
    :minutes (.getUTCMinutes d)
    :seconds (.getUTCSeconds d)}
   )
 )

(defn display-time
 "Pretty-prints hours:minutes:seconds, given time map of the form {:hours x :seconds y}."
 [tm]
 (let [pad (fn [n]
             (if (< n 10)
               (str "0" n)
               n))
       hh (pad (:hours tm))
       mm (pad (:minutes tm))
       ss (pad (:seconds tm))]
   ;;    (str mm ":" ss) <-- if you were to actually use this to update the DOM
   (str hh ":" mm ":" ss)))


(defn find-child-count
 "Counts direct children blocks given a parent block uid"
 [block-uid]
           (or @(dr/q '[ :find (count ?c) .
                        :in $ ?parentuid
						 :where
								[?e :block/uid ?parentuid]
								[?e :block/children ?c]]
                   block-uid) 0)
 )


(defn main [{:keys [block-uid]} & args]
 (let [time-data (r/atom {:elapsed-time 0 })]

   (def interval (js/setInterval #(swap! time-data increase-time) 1000))
   (def time (seconds-to-time (get-in @time-data [:elapsed-time])))
   (fn []
  		(def time (seconds-to-time (get-in @time-data [:elapsed-time])))

      [:div {:style {:display "inline-block"
					          :align-items "left"}
             :class "roam-stopwatch"}
        [:button {:class "bp3-button bp3-minimal bp3-icon-stopwatch"
                  :draggable false
                  :style {:width "100%"
                          :color "#3871CD"
                          :border-radius "3px"
                          :background "#DBE4F6"
                          :border "1px solid #3871CD"}
                  :on-click (fn []
                              (block/create
                                 {:location {:parent-uid block-uid
                                             :order ( find-child-count block-uid)}
                                  :block {:string (display-time time) }})
                              (swap! time-data
                                     (fn [data]
                                       (-> data
                                           (assoc :elapsed-time 0)))) )}
         (or args "Lap")": " (display-time time) ]])))
