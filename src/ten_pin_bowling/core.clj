(ns ten-pin-bowling.core)

:strike ;all 10 pins knoked in one shot
:spare ;all 10 pins knoked in 2 shots
:open ; after the two attempts, some pins remains


(defn frame-score [frames frame-id]
  (let [frame-idx (dec frame-id)
        frame (nth frames frame-idx nil)

        ;;frame bawls
        fbs (nth frames frame-idx nil)
        [fb1 fb2 fb3] fbs

        ;;rounds ahead the frame (current frame inclusive)
        rs (flatten (subvec frames frame-idx))
        [r1 r2 r3] rs

        not-nil? (complement nil?)]

    (cond
      (and (every? not-nil? [r1 r2 r3])
           (or (= 10 fb1) ;<-strike
               (= 10 (+ fb1 fb2)))) ;<-spare
      #_--> (+ r1 r2 r3)

      (every? not-nil? [fb1 fb2])
      #_--> (+ fb1 fb2)

      :default 0)))



(defn start-game
  "Creates an empty score card."
  []
  {:score 0
   :frames []
   :completed false})


(defn valid-frame? [[p1 p2 p3 :as frame] f-id]
  (and
   (every? identity
           (mapcat (juxt (complement nil?)
                         (complement neg?)
                         integer?
                         #(>= 10 %))
                   frame))
   (case (count frame)
     1 (= p1 10)
     2 (< p1 10)
     3 (and (= 10 f-id)
            (>= (+ p1 p2) 10))
     (do (print ">>>>>" f-id) false))))


(defn score
  "Frame succession funciton:
  When incomplete score card and next frame rounds' points given
  it returns the score card after the given frame applied."
  ([card [[p1 p2 p3] :as frame]]
   {:pre [(valid-frame? frame (-> frame count inc))]}
   (score (update card :frames conj frame)))
  ([card]
   {:pre [(every? identity (map #(apply valid-frame? %)
                                (zipmap (:frames card) (range 1 (-> (:frames card) count inc)))))]}

   (let [frames (card :frames)
         frame-scores (map #(frame-score frames %)
                           (range 1 (inc (count frames))))]
     (-> card
         (assoc :score (reduce + frame-scores))
         (assoc :completed (= (count frames) 10))))))
