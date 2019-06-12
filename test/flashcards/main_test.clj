(ns flashcards.main-test
  (:require [clojure.test :refer :all]
            [flashcards.main :refer :all]
            [flashcards.data :refer :all]))
(deftest card-test
  (testing "A new card can be created"
    (let [expected {:question "what is?" :answer "nothing is" :category :facts}]
      (is (=  card_1 expected))
      (is (= (:question card_1) (:question expected)))
      (is (= (:answer card_1) (:answer expected)))
      (is (= (:category card_1) (:category expected))))))

(deftest turn-test
  (testing "A turn can hold a card and returns response for a correct guess"
    (is (= (:card turn_1) card_1))
    (is (= (:guess turn_1) "nothing is"))
    (is (= (:correct? turn_1) true))
    (is (= (:feedback turn_1) "Correct!")))

  (testing "A turn can hold a card and returns response for an incorrect guess"
    (is (= (:card turn_2) card_1))
    (is (= (:guess turn_2) "something is"))
    (is (= (:correct? turn_2) false)
    (is (= (:feedback turn_2) "Incorrect.")))))

(deftest deck-test
  (testing "A deck has some default behaviour"
    (is (= (:cards deck_1) cards))
    (is (= (:count deck_1) 4))
    (is (= ((:cards_in_category deck_1) :life) [card_3 card_4]))))

(deftest round-test
  (testing "A Round can take in a deck and has some functionality"
    (is (= deck_1 (:deck round_1)))
    (is (= [] (:turns round_1)))
    (is (= card_1 (:current_card round_1)))))
