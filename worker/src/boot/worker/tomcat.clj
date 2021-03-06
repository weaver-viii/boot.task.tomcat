;;;-------------------------------------------------------------------------------------------------
;;; Copyright Alan Dipert, Micha Niskin, & jumblerg. All rights reserved. The use and distribution
;;; terms for this software are covered by the Eclipse Public License 1.0 (http://www.eclipse.org/
;;; legal/epl-v10.html). By using this software in any fashion, you are agreeing to be bound by the
;;; terms of this license.  You must not remove this notice, or any other, from this software.
;;;-------------------------------------------------------------------------------------------------

(ns boot.worker.tomcat
  (:require
    [clojure.java.io :as io] )
  (:import
    [org.apache.catalina.startup Tomcat] ))

(defn create [dir war port]
  (.mkdirs (io/file dir "webapps"))
  (doto (Tomcat.)
    (.setBaseDir dir)
    (.setPort port)
    (as-> % (.setParentClassLoader (.getServer %) (.getContextClassLoader (Thread/currentThread))))
    (.addWebapp "" war)
    (.start) ))
