(ns flashcards.main-test
  (:require [clojure.test :refer :all]
            [flashcards.main :refer :all]))

(deftest new-card-test
  (testing "A new card can be created"
    (let [actual (new-card "what is?" "nothing is" :facts)]
    (is (= {:question "what is?" :answer "nothing is" :category :facts} actual)))))
