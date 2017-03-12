(defproject bush-backend "0.1.0-SNAPSHOT"
  :description "Bus-h"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [environ "1.1.0"]
                 [ring/ring-defaults "0.2.3"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-core "1.6.0-RC1"]
                 [ring/ring-devel "1.6.0-RC1"]
                 [ring/ring-jetty-adapter "1.6.0-RC1"]
                 [compojure "1.6.0-beta3"]
                 [yleisradio/new-reliquary "1.0.0"]]
  :plugins [[lein-ring "0.11.0"]
            [lein-environ "1.1.0"]]
  :jar-copier {:java-agents true
               :destination "resources/jars"}
  :ring {:handler bush-backend.core/application}
  :main ^:skip-aot bush-backend.core
  :repl-options {:init-ns bush-backend.core}
  :uberjar-name "bush-backend.jar"
  :profiles {:dev {:dependencies []}
             :test {:dependencies [[ring/ring-mock "0.3.0"]]}
             :production {:plugins [[com.pupeno/jar-copier "0.4.0"]]
                          :prep-tasks ["javac" "compile" "jar-copier"]}
             :uberjar {:aot :all}}
  :java-agents [[com.newrelic.agent.java/newrelic-agent "3.36.0"]])
