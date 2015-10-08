(ns randest.community-example
  (:require [randest.core :refer :all]))

(defn subject-fn [previous-state event]
  (assert (< (count previous-state) 100))
  (case event
    :member-join
    (conj previous-state (str "member-" (rand-int 10000)))

    :member-leave
    (rest previous-state)))

#_(defn start-example! []
  (let [initial-state (list)]
    (start! subject-fn initial-state {:member-join  (* 1000 60 60 24)
                                      :member-leave (* 1000 60 60 24 5)})))
