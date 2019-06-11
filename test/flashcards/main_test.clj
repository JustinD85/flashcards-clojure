(ns flashcards.main-test
  (:require [clojure.test :refer :all]
            [flashcards.main :refer :all]))

(def card_1 (card "what is?" "nothing is" :facts))
(def turn_1 (turn "nothing is" card_1))

(deftest card-test
  (testing "A new card can be created"
    (let [expected {:question "what is?" :answer "nothing is" :category :facts}]
      (is (=  card_1 expected))
      (is (= (:question card_1) (:question expected)))
      (is (= (:answer card_1) (:answer expected)))
      (is (= (:category card_1) (:category expected))))))

(deftest turn-test
  (testing "A turn can hold a card and have some derived values"
    (is (= (:card turn_1) card_1))
    (is (= (:guess turn_1) "nothing is"))
    (is (= (:correct? turn_1) true))
    (is (= (:feedback turn_1) "Correct!"))))
