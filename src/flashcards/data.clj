(ns flashcards.data
  (:require [flashcards.main :refer :all]))

(def card-1 (card "what is?" "nothing is" :facts))
(def card-2 (card "to be" "or not to be" :poetry))
(def card-3 (card "what is the best temp?" "summer" :life))
(def card-4 (card "what is the worst temp?" "winter" :life))

(def cards [card-1 card-2 card-3 card-4])

(def turn-1 (turn "nothing is" card-1))
(def turn-2 (turn "something is" card-1))

(def deck-1 (deck cards))

(def round-1 (round deck-1))
