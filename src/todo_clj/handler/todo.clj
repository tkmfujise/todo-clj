(ns todo-clj.handler.todo
  (:require [compojure.core :refer [defroutes context GET]]
            [todo-clj.view.todo :as view]
            [todo-clj.util.response :as res]))


(defn todo-index [req]
  (-> (view/todo-index-view req)
      res/response
      res/html))


(defn todo-show [id]
  (prn-str id))

(defn todo-new [_]
  (prn-str "todo/new"))


(defroutes todo-routes
  (context "/todo" _
    (GET "/" _ todo-index)
    (GET "/new" _ todo-new)
    (context "/:id" [id]
      (GET "/" _ (todo-show id)))))


