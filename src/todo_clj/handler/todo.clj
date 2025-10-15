(ns todo-clj.handler.todo
  (:require [compojure.core :refer [defroutes context GET]]
            [todo-clj.util.response :as res]))


(def todo-list
  [{:title "朝ごはんを作る"}
   {:title "燃えるゴミを出す"}
   {:title "卵を買って帰る"}
   {:title "お風呂を洗う"}])

(defn todo-index-view [_]
  `("<h1>TODO 一覧</h1>"
    "<ul>"
    ~@(for [{:keys [title]} todo-list]
        (str "<li>" title "</li>"))
    "</ul>"))


(defn todo-index [req]
  (-> (todo-index-view req)
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


