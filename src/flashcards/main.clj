(ns flashcards.main
  (:gen-class))

(defn new-card [question answer category]
  {:question question :answer answer :category category})


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
