(ns flashcards.main-test
  (:require [clojure.test :refer :all]
            [flashcards.main :refer :all]))

(def card_1 (card "what is?" "nothing is" :facts))
(def turn_1 (turn "nothing is" card_1))
(def turn_2 (turn "something is" card_1))

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
