package com.bingqi.service;

import com.bingqi.util.RequestInfo;
import com.bingqi.util.RequestUtil;
import com.bingqi.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

/**
 * 阿里天气服务
 * written by 张弓
 * datetime：2010/04/14
 */
@Service
public class AliWeatherService {
    private RequestInfo info = new RequestInfo();
    private RequestUtil request = new RequestUtil();

    /**
     * ShortForecast 短时预报：提供未来2小时内精准预报
     * @param params 纬度、经度
     */
    public String ShortForecast(String[] params){
        //不同接口的token值不一样
        String token = "bbc0fdc738a3877f3f72f69b1a4d30fe";
        String key = "shortforecast";

        try {
            HttpResponse response = HttpUtils.doPost(info.getHost(), info.getPath()+key, info.getMethod(),
                    request.getHeaders(), request.getQuerys(), request.getBodys(params[0], params[1], token));
//            System.out.println(response.toString());
            //获取response的body
//            System.out.println(EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Forecast24Hours 天气预报24小时：提供未来24小时逐小时天气预报
     * @param params 纬度、经度
     */
    public String Forecast24Hours(String[] params){
        //不同接口的token值不一样
        String token = "1b89050d9f64191d494c806f78e8ea36";
        String key = "forecast24hours";

        try {
            HttpResponse response = HttpUtils.doPost(info.getHost(), info.getPath()+key, info.getMethod(),
                    request.getHeaders(), request.getQuerys(), request.getBodys(params[0], params[1], token));
//            System.out.println(response.toString());
            //获取response的body
//            System.out.println(EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * AqiForecast5Days Aqi预报五天：提供未来5天AQI数据
     * @param params 纬度、经度
     */
    public String AqiForecast5Days(String[] params){
        //不同接口的token值不一样
        String token = "17dbf48dff33b6228f3199dce7b9a6d6";
        String key = "aqiforecast5days";

        try {
            HttpResponse response = HttpUtils.doPost(info.getHost(), info.getPath()+key, info.getMethod(),
                    request.getHeaders(), request.getQuerys(), request.getBodys(params[0], params[1], token));
//            System.out.println(response.toString());
            //获取response的body
//            System.out.println(EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Alert 天气预警：提供各地天气预警信息
     * @param params 纬度、经度
     */
    public String Alert(String[] params){
        //不同接口的token值不一样
        String token = "ff826c205f8f4a59701e64e9e64e01c4";
        String key = "alert";

        try {
            HttpResponse response = HttpUtils.doPost(info.getHost(), info.getPath()+key, info.getMethod(),
                    request.getHeaders(), request.getQuerys(), request.getBodys(params[0], params[1], token));
//            System.out.println(response.toString());
            //获取response的body
//            System.out.println(EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Index 天气指数：提供各项天气生活指数
     * @param params 纬度、经度
     */
    public String Index(String[] params){
        //不同接口的token值不一样
        String token = "42b0c7e2e8d00d6e80d92797fe5360fd";
        String key = "index";

        try {
            HttpResponse response = HttpUtils.doPost(info.getHost(), info.getPath()+key, info.getMethod(),
                    request.getHeaders(), request.getQuerys(), request.getBodys(params[0], params[1], token));
//            System.out.println(response.toString());
            //获取response的body
//            System.out.println(EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Condition 天气指数：提供温度、湿度、风向、风速、紫外线、气压、体感温度等实时数据
     * @param params 纬度、经度
     */
    public String Condition(String[] params){
        //不同接口的token值不一样
        String token = "ff826c205f8f4a59701e64e9e64e01c4";
        String key = "condition";

        try {
            HttpResponse response = HttpUtils.doPost(info.getHost(), info.getPath()+key, info.getMethod(),
                    request.getHeaders(), request.getQuerys(), request.getBodys(params[0], params[1], token));
//            System.out.println(response.toString());
            //获取response的body
//            System.out.println(EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Forecast15Days 天气预报15天：提供未来15天天气预报
     * @param params 纬度、经度
     */
    public String Forecast15Days(String[] params){
        //不同接口的token值不一样
        String token = "7538f7246218bdbf795b329ab09cc524";
        String key = "forecast15days";

        try {
            HttpResponse response = HttpUtils.doPost(info.getHost(), info.getPath()+key, info.getMethod(),
                    request.getHeaders(), request.getQuerys(), request.getBodys(params[0], params[1], token));
//            System.out.println(response.toString());
            //获取response的body
//            System.out.println(EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Limit 限行数据：提供各地限行数据
     * @param params 纬度、经度
     */
    public String Limit(String[] params){
        //不同接口的token值不一样
        String token = "c712899b393c7b262dd7984f6eb52657";
        String key = "limit";

        try {
            HttpResponse response = HttpUtils.doPost(info.getHost(), info.getPath()+key, info.getMethod(),
                    request.getHeaders(), request.getQuerys(), request.getBodys(params[0], params[1], token));
//            System.out.println(response.toString());
            //获取response的body
//            System.out.println(EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Aqi 空气质量指数：提供空气质量指数及分项数据
     * @param params 纬度、经度
     */
    public String Aqi(String[] params){
        //不同接口的token值不一样
        String token = "6e9a127c311094245fc1b2aa6d0a54fd";
        String key = "aqi";

        try {
            HttpResponse response = HttpUtils.doPost(info.getHost(), info.getPath()+key, info.getMethod(),
                    request.getHeaders(), request.getQuerys(), request.getBodys(params[0], params[1], token));
//            System.out.println(response.toString());
            //获取response的body
//            System.out.println(EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
