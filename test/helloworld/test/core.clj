(ns helloworld.test.core
  (:use
    helloworld.core
    clojure.test
    [clojure.data.json :only [read-json]]
    [ring.mock.request :only [request]]))

(deftest word-count-test
  (let [; ring.mock.request/request でレスポンスを取得
        ; 第3引数のマップはQueryStringに展開される
        res (app (request :get "/wc" {:text "hello world hello"}))
        ; JSON形式からマップに変換
        body (-> res :body read-json)]
    ; are って便利
    (are [x y] (= x y)
      200 (:status res)
      2 (:hello body)
      1 (:world body))))

