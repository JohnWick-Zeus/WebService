package com.bingqi.util;

import java.util.HashMap;
import java.util.Map;

/**
 * http请求工具类
 * written by 张弓
 */
public class RequestUtil {
    private RequestInfo info = new RequestInfo();

    public Map<String, String> getHeaders(){
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + info.getAppcode());
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        return headers;
    }

    public Map<String, String> getQuerys(){
        return new HashMap<>();
    }

    public Map<String, String> getBodys(String lat, String lon, String token) {
        Map<String, String> bodys = new HashMap<>();
        bodys.put("lat", lat);
        bodys.put("lon", lon);
        bodys.put("token", token);
        return bodys;
    }
}
