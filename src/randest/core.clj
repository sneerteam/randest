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

(defn- pick-event [event->probability total-probability]
  (loop [event->probabilities (seq event->probability)
         random (rand-int total-probability)]
    (let [[event probability] (first event->probabilities)
          random' (- random probability)]
      (if (<= random' 0)
        event
        (recur (next event->probabilities)
               random')))))

(defn start!
  "event->period example: {:event-a 1      ;Most frequent.
                           :event-b 7
                           :event-c 10}    ;Least frequent."
  [f initial-state event->period]
  (let [event->probability (map-probabilities event->period)
        total-probability (apply + (vals event->probability))
        event (pick-event event->probability total-probability)
        new-state (f initial-state event)]
    (println new-state)
    (recur f new-state event->period)))

(comment
  (randest.core/pick-event {:a 1 :b 2 :c 10}))