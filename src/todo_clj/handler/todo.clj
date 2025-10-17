(ns todo-clj.handler.todo
  (:require [compojure.core :refer [defroutes context GET POST]]
            [todo-clj.db.todo :as todo]
            [todo-clj.view.todo :as view]
            [todo-clj.util.response :as res]))


(defn todo-index [req]
  (let [todo-list (todo/find-todo-all)]
    (-> (view/todo-index-view req todo-list)
        res/response
        res/html)))


(defn todo-show [{:as req :keys [params]}]
  (if-let [todo (todo/find-first-todo (Long/parseLong (:todo-id params)))]
    (-> (view/todo-show-view req todo)
        res/response
        res/html)))


(defn todo-new [req]
  (-> (view/todo-new-view req)
      res/response
      res/html))


(defn todo-new-post [{:as req :keys [params]}]
  (if-let [todo (todo/save-todo (:title params))]
    (-> (res/redirect (str "/todo/" (:id todo)))
        (assoc :flash {:msg "TODOを正常に追加しました。"})
        res/html)))


(defn todo-edit [{:as req :keys [params]}]
  (if-let [todo (todo/find-first-todo (Long/parseLong (:todo-id params)))]
    (-> (view/todo-edit-view req todo)
        res/response
        res/html)))


(defn todo-edit-post [{:as req :keys [params]}]
  (let [todo-id (Long/parseLong (:todo-id params))]
    (if (pos? (first (todo/update-todo todo-id (:title params))))
      (-> (res/redirect (str "/todo/" todo-id))
          (assoc :flash {:msg "TODOを正常に更新しましt"})
          res/html))))


(defroutes todo-routes
  (context "/todo" _
    (GET "/" _ todo-index)
    (GET "/new" _ todo-new)
    (POST "/new" _ todo-new-post)
    (context "/:todo-id" _
      (GET "/" _ todo-show)
      (GET "/edit" _ todo-edit)
      (POST "/edit" _ todo-edit-post))))
