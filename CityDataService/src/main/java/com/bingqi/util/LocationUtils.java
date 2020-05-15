package com.bingqi.util;


/**
 * 根据经纬度计算两点之间的距离
 */
public class LocationUtils {
    /**
     * 赤道半径
     */
    private static double EARTH_RADIOUS = 6378.137;

    private static double rad(double d){
        return d * Math.PI / 180.0;
    }

    /**
     * 通过两点经纬度获取距离
     *
     */
    public double getDistance(double lat1, double lon1, double lat2, double lon2){
        if (lat1 < 0.0 | lon1 < 0.0 | lat2 < 0.0 | lat2 < 0.0){
            System.out.println("请输入正确的经纬度！");
            return 100000000.0;
        }

        double a = rad(lat1) - rad(lat2);
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(rad(lat1)) * Math.cos(rad(lat2))
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIOUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        return s;
    }

}
