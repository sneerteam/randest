(ns randest.example
  (:require [randest.core :refer :all]))

(defn subject-fn [previous-state event]
  (assert (< (.length previous-state) 1000))
  (str previous-state event ", "))

(defn events-count-fn [subject-state]
  10)

(defn event-choose-fn [subject-state selector-number]
  (str "event" selector-number))

(defn start-example! []
  (let [initial-state ""]
    (start! subject-fn initial-state events-count-fn event-choose-fn)))
