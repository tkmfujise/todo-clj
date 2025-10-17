(ns todo-clj.db.todo
  (:require [clojure.java.jdbc :as jdbc]
            [todo-clj.db :as db]))

(defn save-todo [title]
  (let [attr {:title title}
        result (jdbc/insert! db/db-spec :todo attr)]
    {:id (-> (first result) vals first)}))


(defn update-todo [id title]
  (jdbc/update! db/db-spec :todo {:title title} ["id = ?" id]))


(defn delete-todo [id]
  (jdbc/delete! db/db-spec :todo ["id = ?" id]))


(defn find-todo-all []
  (jdbc/query db/db-spec "select * from todo"))


(defn find-first-todo [id]
  (-> (jdbc/query db/db-spec
        ["select * from todo where id = ?" id])
      first))

