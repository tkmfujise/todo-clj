(ns todo-clj.db
  (:require [clojure.java.jdbc :as jdbc]
            [environ.core :refer [env]]))


(def db-spec {
  :classname "org.sqlite.JDBC"
  :subname "database.db"
  :subprotocol "sqlite"})


(defn migrated? []
  (pos? (count (jdbc/query db-spec "select name from sqlite_master where type='table'"))))


(defn migrate []
  (when-not (migrated?)
    (jdbc/db-do-commands
      db-spec
      (jdbc/create-table-ddl :todo [
        [:id :integer :primary :key]
        [:title :text]]))))
