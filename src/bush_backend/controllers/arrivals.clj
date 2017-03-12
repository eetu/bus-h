(ns bush-backend.controllers.arrivals
  (:require [compojure.core :refer [defroutes GET]]
            [ring.util.response :refer [response]]))

(defn next-arrival-to-nearest-stop [line coordinates]
  (response {:line line
             :coordinates coordinates}))

(defroutes routes
   (GET "/bush/action/next-arrival-to-nearest-stop" [line coordinates]
     (next-arrival-to-nearest-stop line coordinates)))