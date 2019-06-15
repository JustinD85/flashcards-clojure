(ns flashcards.runner
 (:gen-class)
 (:require [flashcards.main :refer :all]
           [clojure.string :as s]))

;;grabs desired data
(def flashcard-data-file "resources/german_english.csv")
(def raw-data (slurp flashcard-data-file))

;;helper functions
(defn parse-data [data] (map #(s/split % #",") (s/split data #"\n")))
(def create-card (partial apply card))
(def card-number (comp inc count #(%) #(:turns %)))
(def get-question (comp :question #(%) #(:current-card %)))
(def get-input (comp s/trim read-line))
(def take-turn (comp :feedback #(% (get-input)) #(:take-turn %)))
(def categories (set (map #(last %) (parse-data raw-data))))
(defn correct-by-category [trailing-chars function]
  (apply str (map #(str "\n" (function %) trailing-chars %) categories)))

;;converts to vector of cards
(def cards
  (map create-card (parse-data raw-data)))

;;binds a round
(def round-1
  (round (deck cards)))

(defn -main []
  (println "Welcome to FlashCards!")
  (println "This round has" (count cards) "cards.")
  (println (repeat 15 "_"))

  (while ((:current-card round-1))
    (println "This is card" (card-number round-1)  "out of" (count cards))
    (println "Question:" (get-question round-1))
    (println "Your answer is"(take-turn round-1))
    (println "\n" (repeat 15 "-") "\n")
    (println "\n" (repeat 15 "_") "\n"))

  (println "Total Correct:" ((:number-correct round-1)))
  (println "Percentage correct:" (str (int ((:percent-correct round-1))) "%"))
  (println "\n" (repeat 15 "_") "\n")
  (println "Correct by Category:" (correct-by-category " " (:number-correct-by-category round-1)))
  (println "\n" (repeat 15 "-") "\n")
  (println "\n" (repeat 15 "_") "\n")
  (println "% Correct by Category:" (correct-by-category "% " (:percent-correct-by-category round-1)))
  (println "\n" (repeat 15 "-") "\n"))
