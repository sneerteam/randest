(ns randest.core)

(defn start! [subject-fn initial-state events-count-fn event-choose-fn]
  (let [count (events-count-fn initial-state)
        event (event-choose-fn initial-state (rand-int count))
        new-state (subject-fn initial-state event)]
    (println new-state)
    (recur subject-fn new-state events-count-fn event-choose-fn)))
