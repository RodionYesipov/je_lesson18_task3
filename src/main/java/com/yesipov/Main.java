package com.yesipov;

import java.util.HashMap;
import java.util.Map;

/**
 3*) Сделать то же самое используя библиотеку okhttp3

 https://github.com/square/okhttp
 * */

public class Main {

    private static final String URL = "https://dl.dropboxusercontent.com/s/vxnydq4xjkmefrp/CLUVAL.java";

    public static void main(String[] args) throws Exception {
        int fileSize = Integer.parseInt(HttpUtil.sendRequest(URL, "HEAD", null, null));

        Map<String, String> headers = new HashMap<String, String>();
        //first part
        headers.put("Range", "bytes=0-" + fileSize / 2);
        String json = HttpUtil.sendRequest(URL, "GET", headers, null);
        System.out.println("First part of file !!!\n\n" + json);

        //second part
        headers.put("Range", "bytes=" + (fileSize / 2 + 1) + "-" + fileSize);
        json = HttpUtil.sendRequest(URL, "GET", headers, null);
        System.out.println("\n\nSecond part of file !!!\n\n" + json);

    }
}
