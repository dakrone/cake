(ns cake
  (:use [cake.utils.useful :only [if-ns]])
  (:require [clojure.stacktrace :as stacktrace])
  (:import [java.io File FileInputStream]
           [java.util Properties]))

(def *current-task* nil)
(def *project*      nil)
(def *config*       nil)
(def *script*       nil)
(def *opts*         nil)
(def *pwd*          nil)
(def *env*          nil)
(def *shell-env*    nil)
(def *vars*         nil)
(def *root* (System/getProperty "cake.project"))

(def *ins*  nil)
(def *outs* nil)
(def *errs* nil)

(defn verbose? []
  (boolean (or (:v *opts*) (:verbose *opts*))))

(defn debug? []
  (boolean (or (:d *opts*) (:debug *opts*))))

(defn read-config [file]
  (if (.exists file)
    (with-open [f (FileInputStream. file)]
      (into {} (doto (Properties.) (.load f))))
    {}))

(def *config* (merge (read-config (File. (System/getProperty "user.home") ".cake/config"))
                     (read-config (File. ".cake/config"))))

(if-ns (:require [clj-stacktrace.repl :as clj-stacktrace])
  (do
    (defn print-stacktrace [e]
      (if-let [pst-color (*config* "clj-stacktrace")]
        (do (printf "%s: " (.getName (class e)))
            (clj-stacktrace/pst-on *out* (= "color" pst-color) e))
        (do (stacktrace/print-cause-trace e)
            (flush)))))
  (do
    (defn print-stacktrace [e]
      (stacktrace/print-cause-trace e)
      (flush))))
