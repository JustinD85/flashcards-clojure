(ns flashcards.main
  (:gen-class))

(defn card [question answer category]
  {:question question :answer answer :category category})

(defn turn [guess card]
  (let [correct? (= guess (:answer card))]
    {:card card
     :guess guess
     :correct? correct?
     :feedback (if correct? "Correct!" "Incorrect.")
     :in-category? #(= (:category card) %)
     :correct-in-category #(when (= % (:category card)) correct?)
     }))

(defn deck [cards]
  (let [cards (atom cards)]
    {:cards  (fn [] @cards)
     :count (fn [] (count @cards))
     :remove-card (fn []
                    (let [removed-card (first @cards)]
                      (swap! cards #(rest %))
                      removed-card))
     :cards-in-category  (fn [word]
                           (filter #(= (:category %) word) @cards))}))

(defn round [deck]
  (let [turns (atom []) deck deck
        number-correct (fn [] (count (filter #(:correct? %) @turns)))
        number-correct-by-category (fn [category]
                                     (count
                                      (filter
                                       #((:correct-in-category %) category)
                                       @turns)))]
    {
     :deck (fn [] deck)
     :turns (fn [] @turns)
     :current-card (fn []
                     (first ((:cards deck))))
     :take-turn (fn [guess]
                  (let [new-turn (turn guess (first ((:cards deck))))]
                    (swap! turns #(conj % new-turn))
                    ((:remove-card deck))
                    new-turn))
     :number-correct number-correct
     :number-correct-by-category number-correct-by-category
     :percent-correct (fn [] (/ (* 100 (float (number-correct)))  (count @turns)))
     :percent-correct-by-category (fn [category]
                                    (/ (* 100 (float (number-correct-by-category category)))
                                       (count (filter #((:in-category? %) category) @turns))))}))
