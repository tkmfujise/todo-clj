(ns todo-clj.core
  (:require [compojure.core :refer [routes]]
            [ring.adapter.jetty :as server]
            [ring.middleware.flash :as flash]
            [ring.middleware.keyword-params :as keyword-params]
            [ring.middleware.params :as params]
            [ring.middleware.resource :as resource]
            [ring.middleware.session :as session]
            [todo-clj.handler.main :refer [main-routes]]
            [todo-clj.handler.todo :refer [todo-routes]]
            [todo-clj.middleware :refer [wrap-dev]]
            [environ.core :refer [env]]))


(defonce server (atom nil))


(defn- wrap [handler middleware opt]
  (if (true? opt)
    (middleware handler)
    (if opt
      (middleware handler opt)
      handler)))


(def app
  (-> (routes
       todo-routes
       main-routes)
      (wrap wrap-dev (:dev true))
      (wrap resource/wrap-resource "public")
      (wrap keyword-params/wrap-keyword-params true)
      (wrap params/wrap-params true)
      (wrap flash/wrap-flash true)
      (wrap session/wrap-session true)))


(defn start-server []
  (when-not @server
    (reset! server (server/run-jetty #'app {
      :port 3000 :join? false}))))

(defn stop-server []
  (when @server
    (.stop @server)
    (reset! server nil)))

(defn restart-server []
  (when @server
    (stop-server)
    (start-server)))

