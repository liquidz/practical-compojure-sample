(defproject helloworld "1.0.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [compojure "0.6.5"] ; 12/03時点で最新のタグ
                 [org.clojure/data.json "0.1.1"]
                 [ring/ring-jetty-adapter "0.3.11"]]
  :dev-dependencies [[ring/ring-devel "0.3.11"]
                     [ring-mock "0.1.1"]]
  :web-content "public"
  :main helloworld.core)

