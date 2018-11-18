(ns ten-pin-bowling.core-test
  (:require [clojure.test :refer :all]
            [ten-pin-bowling.core :refer :all]))

(deftest frame-score-test
  (let [;;see https://www.youtube.com/watch?v=aBe71sD8o8c

        card1 (start-game)
        card1-1 (score card1 [2 3])

        frame-set-1 [#_1 [8 2]
                     #_2 [7 3]
                     #_3 [3 4]
                     #_4 [10]
                     #_5 [2 8]
                     #_6 [10]
                     #_7 [10]
                     #_8 [8 0]
                     #_9 [10]
                     #_10 [8 2 9]]

        ;;http://web.cs.wpi.edu/~heineman/html/teaching_/2009_Fall_CS509/Examples/Oct-27/10-27.html
        frame-set-2 [#_1 [1 4]
                     #_2 [4 5]
                     #_3 [6 4]
                     #_4 [5 5]
                     #_5 [10]
                     #_6 [0 1]
                     #_7 [7 3]
                     #_8 [6 4]
                     #_9 [10]
                     #_10 [2 8 6]]

        ;;http://www.tenpin.org.au/index.php?id=875
        frame-set-3 [#_1 [10]
                     #_2 [9 1]
                     #_3 [5 5]
                     #_4 [7 2]
                     #_5 [10]
                     #_6 [10]
                     #_7 [10]
                     #_8 [9 0]
                     #_9 [8 2]
                     #_10 [9 1 10]]]

    (testing "Card 2 each frame scores"
      (is (= 17 (frame-score frame-set-1 0)))
      (is (= 13 (frame-score frame-set-1 1)))
      (is (= 7 (frame-score frame-set-1 2)))
      (is (= 20 (frame-score frame-set-1 3)))
      (is (= 20 (frame-score frame-set-1 4)))
      (is (= 28 (frame-score frame-set-1 5)))
      (is (= 18 (frame-score frame-set-1 6)))
      (is (= 8 (frame-score frame-set-1 7)))
      (is (= 20 (frame-score frame-set-1 8)))
      (is (= 19 (frame-score frame-set-1 9))))

    (testing "Total game score"
      (is (= 170 (:score (score {:frames frame-set-1}))))
      (is (= 133 (:score (score {:frames frame-set-2}))))
      (is (= 187 (:score (score {:frames frame-set-3})))))

    (testing "partial state score"
      (is (= 5 (:score (score card1-1))))
      (is (= 0 (:score (score card1)))))

    (testing "completeness"
      (is (= false (:completed (score card1))))
      (is (= false (:completed (score card1-1))))

      (is (= true (:completed (score {:frames frame-set-1}))))
      (is (= true (:completed (score {:frames frame-set-2}))))
      (is (= true (:completed (score {:frames frame-set-3})))))))




