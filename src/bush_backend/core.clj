(ns bush-backend.core
  (:require [compojure.core :refer [defroutes GET]]
            [environ.core :refer [env]]
            [new-reliquary.ring :refer [wrap-newrelic-transaction]]
            [ring.adapter.jetty :as ring]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [bush-backend.controllers.arrivals :as arrivals])
  (:gen-class))

(defroutes routes
           arrivals/routes)

(defn defaults [handler]
  (-> (wrap-defaults handler api-defaults)
      wrap-json-response))

(def application
  (if (env :dev)
    (-> routes
        wrap-reload
        defaults)
    (-> routes
        defaults
        wrap-newrelic-transaction)))

(defn start [port]
  (ring/run-jetty application {:port port
                               :join? false}))

(defn -main []
  (let [port (Integer. (or (env :port) "8080"))]
    (start port)))