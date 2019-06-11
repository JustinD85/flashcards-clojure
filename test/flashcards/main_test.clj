(ns flashcards.main-test
  (:require [clojure.test :refer :all]
            [flashcards.main :refer :all]))

(deftest new-card-test
  (testing "A new card can be created"
    (let [actual (new-card "what is?" "nothing is" :facts)
          expected {:question "what is?" :answer "nothing is" :category :facts}]
      (is (=  actual expected))
      (is (= (:question actual) (:question expected)))
      (is (= (:answer actual) (:answer expected)))
      (is (= (:category actual) (:category expected))))))

