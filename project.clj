(defproject bush-backend "0.1.0-SNAPSHOT"
  :description "Bus-h"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring/ring-jetty-adapter "1.5.1"]
                 [compojure "1.5.2"]]
  :plugins [[lein-ring "0.11.0"]]
  :ring {:handler bush-backend.core/application}
  :main ^:skip-aot bush-backend.core
  :uberjar-name "bush-backend.jar")
