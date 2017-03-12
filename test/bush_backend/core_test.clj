(ns bush-backend.core-test
  (:require [clojure.test :refer :all]
            [bush-backend.core :refer :all]
            [ring.mock.request :as mock]))

(deftest application-test
  (let [response (application (mock/request :get "/bush/action/next-arrival-to-nearest-stop"))]
    (is (= (:status response) 200))))
