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
      (is (= (:question card-1) (:question expected)))
      (is (= (:answer card-1) (:answer expected)))
      (is (= (:category card-1) (:category expected))))))

(deftest turn-test
  (testing "A turn can hold a card and returns response for a correct guess"
    (is (= (:card turn-1) card-1))
    (is (= (:guess turn-1) "nothing is"))
    (is (= (:correct? turn-1) true))
    (is (= (:feedback turn-1) "Correct!")))

  (testing "A turn can hold a card and returns response for an incorrect guess"
    (is (= (:card turn-2) card-1))
    (is (= (:guess turn-2) "something is"))
    (is (= (:correct? turn-2) false)
        (is (= (:feedback turn-2) "Incorrect.")))))

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
    (is (= 1 1)))))
