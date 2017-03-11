(ns bush-backend.core
  (:require [compojure.core :refer [defroutes GET]]
            [environ.core :refer [env]]
            [new-reliquary.ring :refer [wrap-newrelic-transaction]]
            [ring.adapter.jetty :as ring]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.route :as route]
            [compojure.handler :as handler])
  (:gen-class))

(defroutes routes
  (GET "/" [] "Hello world!"))

(def application
  (let [handler (handler/site routes)]
    (if (env :dev)
      (-> handler
          wrap-reload)
      (-> handler
          wrap-params
          wrap-newrelic-transaction))))

(defn start [port]
  (ring/run-jetty application {:port port
                               :join? false}))

(defn -main []
  (let [port (Integer. (or (System/getenv "PORT") "8080"))]
    (start port)))