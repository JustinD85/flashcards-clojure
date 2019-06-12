(ns flashcards.data
  (:require [flashcards.main :refer :all]))


(def card_1 (card "what is?" "nothing is" :facts))
(def card_2 (card "to be" "or not to be" :poetry))
(def card_3 (card "what is the best temp?" "summer" :life))
(def card_4 (card "what is the worst temp?" "winter" :life))

(def cards [card_1 card_2 card_3 card_4])

(def turn_1 (turn "nothing is" card_1))
(def turn_2 (turn "something is" card_1))

(def deck_1 (deck cards))

(def round_1 (round deck_1))

