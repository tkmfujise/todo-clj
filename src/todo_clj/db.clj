(ns todo-clj.db
  (:require [clojure.java.jdbc :as jdbc]))


(def db-spec {
  :classname "org.sqlite.JDBC"
  :subname "database.db"
  :subprotocol "sqlite"})


(defn migrate []
  (jdbc/db-do-commands
    db-spec
    (jdbc/create-table-ddl :todo [
      [:id :integer :primary :key]
      [:title :text]])))
