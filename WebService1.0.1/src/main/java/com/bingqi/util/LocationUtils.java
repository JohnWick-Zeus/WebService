package com.bingqi.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
 
import java.net.URL;
 
/**
 * @Description 地理位置计算类
 */
public class LocationUtils {
    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return
     */
    public double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        return s;
    }

    /**
     * 根据经纬度获取地点名称
     *
     * @param log
     * @param lat
     * @return
     */
    public static String getAdd(String log, String lat) {
        //lat 小  log  大
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l=" + lat + "," + log + "&type=010";
        String res = "";
        try {
            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line + "\n";
            }
            in.close();
        } catch (Exception e) {
            System.out.println("error in wapaction,and e is " + e.getMessage());
        }

        return getAddressName(res);
    }

    /**
     * 获取地点名称
     *
     * @param jsonString
     * @return
     */
    public static String getAddressName(String jsonString) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("addrList"));
        JSONObject j_2 = (JSONObject) JSONObject.toJSON(jsonArray.get(0));
        String allAdd = j_2.getString("admName");
        String addr = j_2.getString("addr");
        String name = j_2.getString("name");
        String arr[] = allAdd.split(",");
        String addressName = arr[0] + arr[1] + arr[2] + addr + name;
        return addressName;
    }
}
 
 
