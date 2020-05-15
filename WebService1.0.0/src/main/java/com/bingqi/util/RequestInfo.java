package com.bingqi.util;

public class RequestInfo {
    private String host = "http://aliv8.data.moji.com";
    private String path = "/whapi/json/aliweather/";
    private String method = "POST";
    private String appcode = "853d1144689a40fa9a1eb1bb9c12f396";

    public String getHost() {
        return host;
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    public String getAppcode() {
        return appcode;
    }

}
