(ns randest.core)

(defn- normalize [largest-period period]
  (->> period
    (/ largest-period)
    (* 1000)       ;Arbitrary large number to avoid too much loss of precision from rounding to int
    (int)))

(defn- map-probabilities [event->period]
  (let [largest-period (apply max (vals event->period))]
    (into {}
      (for [[event period] event->period]
        [event (normalize largest-period period)]))))

;event->period example: {:event-a 1      ;Most frequent.
;                        :event-b 7
;                        :event-c 10}    ;Least frequent.
(defn pick-event [event->period]
  (let [event->probability (map-probabilities event->period)
        total-probability (apply + (vals event->probability))]
    (loop [event->probabilities (seq event->probability)
           random (rand-int total-probability)]
      (let [[event probability] (first event->probabilities)
            random' (- random probability)]
        (if (<= random' 0)
          event
          (recur (next event->probabilities)
                 random'))))))

#_(defn start! [subject-fn initial-state event->period]
  (let [new-state (subject-fn initial-state event)]
    (println new-state)
    (recur subject-fn new-state events-count-fn event-choose-fn)))

(comment
  (randest.core/pick-event {:a 1 :b 2 :c 10}))