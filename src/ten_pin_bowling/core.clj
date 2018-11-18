(ns ten-pin-bowling.core)

(defn start-game
  "Creates an empty score card."
  []
  {:score 0
   :frames []
   :completed false})

(defn valid-frame?
  "When frame points (integer vector) frame position is given,
  this returns true if valid and false otherwise"
  [[p1 p2 p3 :as frame] f-id]
  {:pre [(every? integer? frame)]}
  (and
   (every? identity (mapcat (juxt (complement nil?)
                                  (complement neg?)
                                  #(>= 10 %))
                            frame))
   (case (count frame)
     1 (= p1 10)
     2 (< p1 10)
     3 (and (= 9 f-id)
            (>= (+ p1 p2) 10))
     false)))

(defn frame-score
  "Calculates the score of a single frame at f-id.
  Due to the scoring rules, other frames are also needed"
  [frames f-id]
  (let [frame (nth frames f-id nil)

        ;;frame bawls
        fbs (nth frames f-id nil)
        [fb1 fb2 fb3] fbs

        ;;rounds ahead the frame (current frame inclusive)
        rs (flatten (subvec frames f-id))
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

(defn score
  "Frame succession funciton:
  When incomplete score card and new frame points given
  it returns the score card after the given frame applied."
  ([card [p1 p2 p3 :as frame]]
   {:pre [(valid-frame? frame (-> frame count dec))]}
   (score (update card :frames conj frame)))

  ([card]
   {:pre [(every? identity (map #(apply valid-frame? %)
                                (zipmap (:frames card)
                                        (range (-> card :frames count)))))]}

   (let [frames (card :frames)
         frame-scores (map #(frame-score frames %)
                           (range (count frames)))]
     (-> card
         (assoc :score (reduce + frame-scores))
         (assoc :completed (= (count frames) 10))))))
