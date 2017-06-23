(ns three-sat.three-sat
  (require [clojure.string :refer [lower-case upper-case]])
  (import (puzzle Evaluator)
          (java.util ArrayList)))

(def unsatisfiable '((\X \Y \Z) (\X \Y \z) (\X \y \Z) (\X \y \z) (\x \Y \Z) (\x \Y \z) (\x \y \Z) (\x \y \z)))

(def satisfiable '((\X \Y \Z) (\X \Y \z) (\X \y \Z) (\X \y \z)))

(defn horizontal-walls [number-of-clauses]
  [(concat '(\5 \1) (repeat (* 3 number-of-clauses) \5) '(\3 \5))
   (concat '(\8 \2) (repeat number-of-clauses \8))
   (concat (repeat number-of-clauses \9) '(\4 \9))])

(defn vertical-walls [variables]
  [(concat (repeat (count variables) \6) '(1) variables (repeat (count variables) \6) '(\2))
   (concat (repeat (count variables) \7) '(3) (map first (map lower-case variables)) (repeat (count variables) \7) '(\4))])

(defn variable-words [variables formula]
  (for [variable variables
        :let [guard (char (+ 200 (int variable)))]]
    (concat
      [guard variable]
      (map #(if (some #{variable} %) \T \F) formula)
      (map #(if (some #{(first (lower-case variable))} %) \T \F) formula)
      [(first (lower-case variable)) guard])))

(defn clause-words [number-of-clauses number-of-variables]
  (for [clause-index (range number-of-clauses)]
    (concat [\T]
            (repeat (- number-of-variables 1) \F)
            (repeat (+ 1 number-of-variables) (char (+ 300 clause-index))))))

(defn create-words [formula]
  (let [number-of-clauses (count formula)
        variables (->> formula (flatten) (map upper-case) (map first) (apply sorted-set))
        words (concat (horizontal-walls number-of-clauses)
                      (vertical-walls variables)
                      (variable-words variables formula)
                      (clause-words number-of-clauses (count variables)))]
    (map #(apply str %) words)))

(defn reduction [formula]
  (->> formula
       (create-words)
       (ArrayList.)
       (Evaluator/findSmallestGrid)
       (.get)
       (.toString)
       (print)))


