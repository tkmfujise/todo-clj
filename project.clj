(defproject todo-clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "https://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.12.2"]
                 [ring "1.15.3"]
                 [compojure "1.7.2"]
                 [hiccup "2.0.0"]
                 [environ "1.2.0"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [org.xerial/sqlite-jdbc "3.49.1.0"]
                 [bouncer "1.0.1"]
                 [ring/ring-defaults "0.7.0"]
                 [metosin/ring-http-response "0.9.5"]
                 [slingshot "0.12.2"]
                 [potemkin "0.4.8"]]
  :plugins [[lein-environ "1.2.0"]]
  :uberjar-name "todo-clj.jar"
  :profiles
  {:dev {:dependencies [[prone "2021-04-23"]]
         :env {:dev true}}
   :uberjar {:aot :all
             :main todo-clj.main}}
  :repl-options {:init-ns todo-clj.core})
