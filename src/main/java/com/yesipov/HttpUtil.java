package com.yesipov;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.net.HttpURLConnection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpUtil {
    private static boolean responseStatusCodeCheck(int code) {
        if (code == HttpURLConnection.HTTP_OK || code == HttpURLConnection.HTTP_PARTIAL) {
            return true;
        }
        return false;
    }

    //////
    public static String sendRequest(String url, String requestType, Map<String, String> headers, String request) throws Exception {
        String result = null;
        OkHttpClient urlConnection = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        if (requestType.equals("GET")) {
            Request reqst = new Request.Builder()
                    .get()
                    .url(url)
                    .headers(Headers.of(headers))
                    .build();
            Response response = urlConnection.newCall(reqst).execute();

            if (responseStatusCodeCheck(response.code())) {
                result = response.body().string();
            } else {
                throw new Exception(response.message());
            }
        } else if (requestType.equals("HEAD")) {
            Request reqst = new Request.Builder()
                    .head()
                    .url(url)
                    .build();
            Response response = urlConnection.newCall(reqst).execute();

            if (responseStatusCodeCheck(response.code())) {
                result = response.header("content-length");
            } else {
                throw new Exception(response.message());
            }
        }
        return result;
    }
}
