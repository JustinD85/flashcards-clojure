(ns flashcards.main
  (:gen-class))

(defn card [question answer category]
  {:question question :answer answer :category category})

(defn turn [guess card]
  (let [correct? (= guess (:answer card))]
    {:card card
     :guess guess
     :correct? correct?
     :feedback (if correct? "Correct!" "Incorrect.")}))

(defn deck [cards]
  {:cards cards
   :count (count cards)
   :cards_in_category #(with-category cards %)})

;;private

(defn with-category [cards word]
  (filter #(= (:category %) word) cards))

