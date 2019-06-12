(ns flashcards.main-test
  (:require [clojure.test :refer :all]
            [flashcards.main :refer :all]))

(def card-1 (card "what is?" "nothing is" :facts))
(def card-2 (card "to be" "or not to be" :poetry))
(def card-3 (card "what is the best temp?" "summer" :life))
(def card-4 (card "what is the worst temp?" "winter" :life))

(def cards [card-1 card-2 card-3 card-4])

(def turn-1 (turn "nothing is" card-1))
(def turn-2 (turn "something is" card-1))

(def deck-1 (deck cards))

(def round-1 (round deck-1))

(deftest card-test
  (testing "A new card can be created"
    (let [expected {:question "what is?" :answer "nothing is" :category :facts}]
      (is (=  card-1 expected))
      (is (= "what is?" (:question card-1)))
      (is (= "nothing is" (:answer card-1)))
      (is (= :facts (:category card-1))))))

(deftest turn-test
  (testing "A turn can hold a card and returns response for a correct guess"
    (is (= (:card turn-1) card-1))
    (is (= (:guess turn-1) "nothing is"))
    (is (= (:correct? turn-1) true))
    (is (= (:feedback turn-1) "Correct!"))
    (is ((:in-category? turn-1) :facts))
    (not (= ((:in-category? turn-1) :not-facts)))
    (is (= true ((:correct-in-category turn-1) :facts )))))

  (testing "A turn can hold a card and returns response for an incorrect guess"
    (is (= (:card turn-2) card-1))
    (is (= (:guess turn-2) "something is"))
    (is (= (:correct? turn-2) false)
        (is (= (:feedback turn-2) "Incorrect."))))

(deftest deck-test
  (testing "Deck has all cards"
    (is (= ((:cards deck-1)) cards)))
  (testing "Can return count of cards"
    (is (= ((:count deck-1)) 4)))
  (testing "Can return cards that match category"
    (is (= ((:cards-in-category deck-1) :life) [card-3 card-4])))
  (testing "It can remove a card"
    (is (= (first cards) ((:remove-card deck-1))))
    (is (= (rest cards) ((:cards deck-1))))))

(deftest round-test
  (let  [deck-1 (deck cards) round-1 (round deck-1)]
    (testing "A Round can take in a deck and has some functionality"
      (is (= deck-1 ((:deck round-1))))
      (is (= [] ((:turns round-1))))
      (is (= card-1 ((:current-card round-1)))))

    (testing "A Round can take a turn"
      (let [turn ((:take-turn round-1) "nothing is")]

        (is (= turn (first ((:turns round-1)))))
        (is (:correct? turn))
        (is (= [turn] ((:turns round-1))))
        (is (= 1 (count ((:turns round-1)))))
        (is (= "Correct!" (:feedback (last ((:turns round-1))))))
        (is (= 1 ((:number-correct round-1))))
        (is
         ((:take-turn round-1) "Wolf")
         (= 2 (count ((:turns round-1)))))
        (is (= "Correct!" (:feedback (first ((:turns round-1))))))
        (is (= "Incorrect." (:feedback (last ((:turns round-1))))))
        (is (not (:correct? (last ((:turns round-1))))))
        (is (= 1 ((:number-correct round-1))))
        (is (= 0 ((:number-correct-by-category round-1) :none)))
        (is (= 0 ((:number-correct-by-category round-1) :poetry)))
        (is (= 1 ((:number-correct-by-category round-1) :facts)))
        (is (= 50.0 ((:percent-correct round-1))))
        (is (= 100.0 ((:percent-correct-by-category round-1) :facts)))
        (is (= card-3 ((:current-card round-1))))))))
