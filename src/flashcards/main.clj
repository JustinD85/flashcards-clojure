(ns flashcards.main
  (:gen-class))

(defn card [question answer category]
  {:question question :answer answer :category category})

(defn turn [guess card]
  (let [correct? (= guess (:answer card))]
  {:card card
   :guess guess
   :correct? correct?
   :feedback (if correct? "Correct!" "Incorrect.")}))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
