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
  (let [cards (atom cards)]
    {:cards  (fn [] @cards)
     :count (fn [] (count @cards))
     :remove-card (fn []
                    (let [removed-card (first @cards)]
                      (swap! cards #(rest %))
                      removed-card))
     :cards-in-category  (fn [word]
                           (filter #(= (:category %) word) @cards))}))

(defn round [deck-with-cards]
  (let [turns (atom []) deck deck-with-cards]
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
     :number-correct (fn []
                       (count (filter #(:correct? %) @turns)))}))
