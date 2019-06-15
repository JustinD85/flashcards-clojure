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

;;converts to vector of cards
(def cards
  (map create-card (parse-data raw-data)))



(defn -main []
  (println "Welcome"))
