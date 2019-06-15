(ns flashcards.runner
 (:gen-class)
 (:require [flashcards.main :refer :all]
           [clojure.string :as s]))

;;grabs desired data
(def german-words-file "resources/german_english.csv")
(def raw-data (slurp german-words-file))

;;helper functions
(defn parse-data [data] (map #(s/split % #",") (s/split data #"\n")))
(def create-card (partial apply card))
(def card-number (comp inc count #(%) #(:turns %)))
(def get-question (comp :question #(%) #(:current-card %)))
(def get-input (comp s/trim read-line))
(def take-turn (comp :feedback #(% (get-input)) #(:take-turn %)))
(def categories (set (map #(last %) (parse-data raw-data))))

;;converts to vector of cards
(def cards
  (map create-card (parse-data raw-data)))

;; binds a deck
(def german-english-deck
  (deck cards))

;;TODO multiple rounds
;;TODO multiple decks

;;binds a round
(def round-1
  (round german-english-deck))

(defn -main []
  (println "Welcome to FlashCards!")
  (println "This round has" (count cards) "cards.")
  (println (repeat 15 "_"))

  (while ((:current-card round-1))
    (println "This is card" (card-number round-1)  "out of" (count cards))
    (println "Question:" (get-question round-1))
    (println "Your answer is"(take-turn round-1))
    (println (repeat 15 "-"))
    (println )
    (println )
    (println (repeat 15 "_")))

  (println "Total Correct:" ((:number-correct round-1)))
  (println (repeat 15 "_"))
  (println "Percentage correct:" (str (int ((:percent-correct round-1))) "%"))
  (println (repeat 15 "_"))
  (println "Correct by Category:" (apply str (map #(str "\n" ((:number-correct-by-category round-1) %) " " %) categories)))
  (println (repeat 15 "_"))
  (println "% Correct by Category:" (apply str (map #(str "\n" (int ((:percent-correct-by-category round-1) %)) "% " %) categories))))
(println (repeat 15 "_"))
