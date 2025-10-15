(ns todo-clj.view.main
  (:require [todo-clj.view.layout :as layout]))


(defn home-view [req]
  (->> (list
        [:h2 "ホーム画面"]
        [:a {:href "/todo"} "TODO一覧"])
       (layout/common req)))

