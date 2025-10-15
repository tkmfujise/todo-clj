(ns todo-clj.view.todo
  (:require [todo-clj.view.layout :as layout]))


(def todo-list
  [{:title "朝ごはんを作る"}
   {:title "燃えるゴミを出す"}
   {:title "卵を買って帰る"}
   {:title "お風呂を洗う"}])


(defn todo-index-view [req]
  (->> `([:h1 "TODO一覧"]
        [:ul
         ~@(for [{:keys [title]} todo-list]
              [:li title])])
        (layout/common req)))

