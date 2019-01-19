package com.itheima.demo.framework;

/**
 * Created by crowndint on 2019/1/19.
 */
public class HttpProtocol {

    public static Object send(URL url, Invocation invocation) {

        HttpClient httpClient = new HttpClient();
        return httpClient.post(url.getHostname(), url.getPort(), invocation);
    }
}
