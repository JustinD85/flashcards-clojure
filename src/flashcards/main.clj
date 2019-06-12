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
   :cards_in_category (fn [word]
                        (filter (fn [card]
                                  (= (:category card) word)) cards))})
