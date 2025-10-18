(ns todo-clj.util.response
  (:require [potemkin :as p]
            [ring.util.http-response :as res]))


(defmacro import-ns [ns-sym]
  (do
    `(p/import-vars
       [~ns-sym
        ~@(map first (ns-publics ns-sym))])))


(import-ns ring.util.http-response)


; (def response #'res/response)
; (alter-meta! #'response #(merge % (meta #'res/response)))

; (def redirect #'res/redirect)
; (alter-meta! #'redirect #(merge % (meta #'res/redirect)))


(defn html [res]
  (res/content-type res "text/html; charset=utf-8"))
