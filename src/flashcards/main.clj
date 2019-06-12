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
   :cards_in_category  (fn [word]
                         (filter #(= (:category %) word) cards))})


(defn round [deck-with-cards]
  (let [turns [] deck deck-with-cards]
    {:deck deck
     :turns turns
     :current_card (first (:cards deck))}))
