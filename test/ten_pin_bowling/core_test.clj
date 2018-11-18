(ns ten-pin-bowling.core-test
  (:require [clojure.test :refer :all]
            [ten-pin-bowling.core :refer :all]))

(deftest frame-score-test
  (let [
        card1 [#_1 [0 0]
               #_2 [5 3]
               #_3 [4 3]
               #_4 [10 0]
               #_5 [7 3]
               #_6 [0 10]
               #_7 [4 6]
               #_8 [10 10]
               #_9 [10 10]
               #_10 [1 9 5]]

        card2 [#_1 [8 2]
               #_2 [7 3]
               #_3 [3 4]
               #_4 [10]
               #_5 [2 8]
               #_6 [10]
               #_7 [10]
               #_8 [8 0]
               #_9 [10]
               #_10 [8 2 9]]

        #_card2 #_ [#_1 [8 2]
               #_2 [7 3]
               #_3 [3 4]
               #_4 [10]
               #_5 [2 8]
               #_6 [10]
               #_7 [10]
               #_8 [8 0]
               #_9 [10]
               #_10 [8 2 9]]

        card3 [#_1 [0 0]
               #_2 [5 3]
               #_3 [4 4]
               #_4 [10 0]
               #_5 [7 3]
               #_6 [0 10]
               #_7 [4 6]
               #_8 [10 10]
               #_9 [10 10]
               #_10 [10 10 10]]

        ;;http://web.cs.wpi.edu/~heineman/html/teaching_/2009_Fall_CS509/Examples/Oct-27/10-27.html
        card4 [#_1 [1 4]
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
        card5 [#_1 [10]
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
      (is (= 17 (frame-score card2 1)))
      (is (= 13 (frame-score card2 2)))
      (is (= 7 (frame-score card2 3)))
      (is (= 20 (frame-score card2 4)))
      (is (= 20 (frame-score card2 5)))
      (is (= 28 (frame-score card2 6)))
      (is (= 18 (frame-score card2 7)))
      (is (= 8 (frame-score card2 8)))
      (is (= 20 (frame-score card2 9)))
      (is (= 19 (frame-score card2 10))))

    (testing "Total game score"
      (is (= 170 (:score (score {:frames card2}))))
      (is (= 133 (:score (score {:frames card4}))))
      (is (= 187 (:score (score {:frames card5})))))))


