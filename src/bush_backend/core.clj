(ns bush-backend.core
  (:require [compojure.core :refer [defroutes GET]]
            [ring.adapter.jetty :as ring]
            [compojure.route :as route]
            [compojure.handler :as handler])
  (:gen-class))

(defroutes routes
  (GET "/" [] "Hello world!"))

(def application (handler/site routes))

(defn start [port]
  (ring/run-jetty application {:port port
                               :join? false}))

(defn -main []
  (let [port (Integer. (or (System/getenv "PORT") "8080"))]
    (start port)))