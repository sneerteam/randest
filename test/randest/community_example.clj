(ns randest.community-example
  (:require [randest.core :refer :all]))

(defn subject-fn [previous-state event]
  (assert (< (count previous-state) 100))
  (case event
    :join
    (conj previous-state (str "member-" (rand-int 10000)))

    :leave
    (rest previous-state)))

(defn events-count-fn [subject-state]
  3)

(defn event-choose-fn [subject-state selector-number]
  (if (zero? selector-number) :leave :join))

(defn start-example! []
  (let [initial-state (list)]
    (start! subject-fn initial-state events-count-fn event-choose-fn)))
