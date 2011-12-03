(ns helloworld.core
  (:use
    [clojure.data.json :only [json-str]]
    [compojure.core :only [defroutes GET]]
    ; filesを追加
    [compojure.route :only [not-found files]]
    [compojure.handler :only [api site]]
    [ring.middleware reload stacktrace]
    [ring.adapter.jetty :only [run-jetty]]))

(defn word-count [text]
  (->> text (re-seq #"\w+") (map (fn [x] {x 1})) (apply (partial merge-with +))))

(defn index
  "/ にアクセスされたときの処理"
  [req]
  "hello world")

(defroutes api-route
  (GET "/wc" {{:keys [text]} :params}
    (json-str (word-count text))))

(defroutes main-route
  (GET "/" req (index req)) ; 処理を関数に
  (GET "/err" _ (throw (Exception.))) ; stacktraceの確認用
  (files "/") ; publicディレクトリを"/"にひもづける
  ; defroutesは定義した順に処理するためnot-foundは最後に書く
  (not-found "NOT FOUND"))

(defroutes total-route
  (api api-route)
  (site main-route))

(defroutes app
  (-> total-route
    (wrap-reload '[helloworld.core])
    wrap-stacktrace))

(defn -main []
  ; heroku向けのport取得
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "8080"))]
    (run-jetty app {:port port})))






