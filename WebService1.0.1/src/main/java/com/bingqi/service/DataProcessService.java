package com.bingqi.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bingqi.util.DataProcessUtil;
import com.bingqi.util.DateUtil;
import com.bingqi.util.Point;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 天气资料处理服务
 * written by 张弓
 * datetime：2010/04/14
 */
@Service
public class DataProcessService {
    private DataProcessUtil dp = new DataProcessUtil();
    private DateUtil date = new DateUtil();

    /**
     * 根据数据调用天气预报15天接口，将数据拆解（按照固定要求）
     * @param points 数据点集合
     */
    public ArrayList<ArrayList<String>> Point2Forecast15DaysData(Vector<Point> points){
        if(points != null) {
            try {
                ArrayList<ArrayList<String>> allData = new ArrayList<>(); // 一个点的所有数据
                for (Point point : points) {
                    ArrayList<String> lineData; // 一个点的一天数据

                    String[] params = new String[]{String.valueOf(point.getX()), String.valueOf(point.getY())};
                    String forecast15days = dp.GetApiValue("Forecast15Days", params);
                    JSONObject data = JSON.parseObject(forecast15days).getJSONObject("data");
                    JSONObject city = data.getJSONObject("city");
                    JSONArray forecast = data.getJSONArray("forecast");

                    for (int i = 0; i < forecast.size(); i++) {
                        // YBNR：预报内容
                        String YBNR = forecast.getString(i);

                        // uuid码：
                        String ID = UUID.randomUUID().toString();

                        // _YBRQ_MC：预报日期_名称、年、月、日
                        String _YBRQ_MC = forecast.getJSONObject(i).getString("predictDate");

                        // _YBWZ_WD、_YBWZ_JD：预报位置纬度、经度
                        String _YBWZ_WD = params[0];
                        String _YBWZ_JD = params[1];

                        // _YBWZ_MC：预报位置_名称
                        String _YBWZ_MC = city.getString("counname") + city.getString("pname") +city.getString("secondaryname")+city.getString("name");

                        // SJGXRQ：数据更新时间
                        String SJGXRQ = forecast.getJSONObject(1).get("updatetime").toString();

                        lineData = new ArrayList<>(Arrays.asList(YBNR, ID, _YBRQ_MC, _YBRQ_MC.substring(0, 4), _YBRQ_MC.substring(5, 7), _YBRQ_MC.substring(8, 10),
                                _YBWZ_WD, _YBWZ_JD, "度", _YBWZ_MC, "墨迹天气", SJGXRQ, String.valueOf(date.getYear(SJGXRQ)), String.valueOf(date.getMonth(SJGXRQ)),
                                String.valueOf(date.getDay(SJGXRQ)), String.valueOf(date.getHour(SJGXRQ)), String.valueOf(date.getMinute(SJGXRQ)), String.valueOf(date.getSecond(SJGXRQ))));
                        allData.add(lineData);
//                        System.out.println(lineData.get(0));
                    }
                }
                System.out.println("数据生成成功！");
                return allData;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    /**
     * 根据数据调用天气预报24小时接口，将数据拆解（按照固定要求）
     * @param points 数据点集合
     */
    public ArrayList<ArrayList<String>> Point2Forecast24HoursData(Vector<Point> points){
        if(points != null) {
            try {
                ArrayList<ArrayList<String>> allData = new ArrayList<>(); // 一个点的所有数据
                for (Point point : points) {
                    ArrayList<String> lineData; // 一个点的一天数据

                    String[] params = new String[]{String.valueOf(point.getX()), String.valueOf(point.getY())};
                    String forecast15days = dp.GetApiValue("Forecast24Hours", params);
                    JSONObject data = JSON.parseObject(forecast15days).getJSONObject("data");
                    JSONObject city = data.getJSONObject("city");
                    JSONArray hourly = data.getJSONArray("hourly");

                    for (int i = 0; i < hourly.size(); i++) {
                        // YBNR：预报内容
                        String YBNR = hourly.getString(i);

                        // uuid码：
                        String ID = UUID.randomUUID().toString();

                        // _YBRQ_MC：预报日期_名称、年、月、日、时
                        String _YBRQ_MC = hourly.getJSONObject(i).getString("date");
                        String _YBRQ_S = hourly.getJSONObject(i).getString("hour");

                        // _YBWZ_WD、_YBWZ_JD：预报位置纬度、经度
                        String _YBWZ_WD = params[0];
                        String _YBWZ_JD = params[1];

                        // _YBWZ_MC：预报位置_名称
                        String _YBWZ_MC = city.getString("counname") + city.getString("pname") +city.getString("secondaryname")+city.getString("name");

                        // SJGXRQ：数据更新时间 注意官网给的返回数据有误差！
                        String SJGXRQ = hourly.getJSONObject(1).get("updatetime").toString();

                        lineData = new ArrayList<>(Arrays.asList(YBNR, ID, _YBRQ_MC, _YBRQ_MC.substring(0, 4), _YBRQ_MC.substring(5, 7), _YBRQ_MC.substring(8, 10), _YBRQ_S,
                                _YBWZ_WD, _YBWZ_JD, "度", _YBWZ_MC, "墨迹天气", SJGXRQ, String.valueOf(date.getYear(SJGXRQ)), String.valueOf(date.getMonth(SJGXRQ)),
                                String.valueOf(date.getDay(SJGXRQ)), String.valueOf(date.getHour(SJGXRQ)), String.valueOf(date.getMinute(SJGXRQ)), String.valueOf(date.getSecond(SJGXRQ))));
                        allData.add(lineData);
//                        System.out.println(lineData);
                    }
                }
                System.out.println("数据生成成功！");
                return allData;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    /**
     * 根据数据调用天气预报天气实况，将数据拆解（按照固定要求）
     * @param points 数据点集合
     */
    public ArrayList<ArrayList<String>> Point2ConditionData(Vector<Point> points){
        if(points != null) {
            try {
                ArrayList<ArrayList<String>> allData = new ArrayList<>();
                ArrayList<String> lineData; // 一个点的一天数据
                for (Point point : points) {
                    String[] params = new String[]{String.valueOf(point.getX()), String.valueOf(point.getY())};
                    String conditionData = dp.GetApiValue("Condition", params);
                    JSONObject data = JSON.parseObject(conditionData).getJSONObject("data");
                    JSONObject city = data.getJSONObject("city");
                    JSONObject condition = data.getJSONObject("condition");

                    // YBNR：预报内容
                    String YBNR = data.getString("condition");

                    // uuid码：
                    String ID = UUID.randomUUID().toString();

                    // _YBRQ_MC：预报日期_名称、年、月、日，condition这里的接口没有数据，直接使用updatetime
                    String _YBRQ_MC = condition.getString("sunRise").substring(0,10);

                    // _YBWZ_WD、_YBWZ_JD：预报位置纬度、经度
                    String _YBWZ_WD = params[0];
                    String _YBWZ_JD = params[1];

                    // _YBWZ_MC：预报位置_名称
//                    String _YBWZ_MC = city.getString("counname") + city.getString("pname") +city.getString("secondaryname")+city.getString("name");
                    String _YBWZ_MC = city.getString("pname") +city.getString("secondaryname");

                    // SJGXRQ：数据更新时间 注意官网给的返回数据有误差！
                    String SJGXRQ = condition.get("updatetime").toString();
                    String year = String.valueOf(date.getYear(SJGXRQ));
                    String month = String.valueOf(date.getMonth(SJGXRQ));
                    String day = String.valueOf(date.getDay(SJGXRQ));

                    lineData = new ArrayList<>(Arrays.asList(YBNR, ID, _YBRQ_MC, year, month, day, _YBWZ_WD, _YBWZ_JD, "度", _YBWZ_MC, "墨迹天气", SJGXRQ,
                            year, month, day, String.valueOf(date.getHour(SJGXRQ)), String.valueOf(date.getMinute(SJGXRQ)), String.valueOf(date.getSecond(SJGXRQ))));
                    allData.add(lineData);
//                    System.out.println(lineData);
                }
                System.out.println("数据生成成功！");
                return allData;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    /**
     * title
     * @param apiName 接口名：TQYBDT
     * @return ArrayList<String>
     */
    public ArrayList<String> GetTitleStr(String apiName){
        if (apiName == null){
            System.out.println("接口名不正确/已更改");
        }
        assert apiName != null;
        if (apiName.equals("TQYBDTZXS")) {
            return new ArrayList<>(Arrays.asList("YBNR","ID","_YBRQ__MC","_YBRQ__N","_YBRQ__Y","_YBRQ__R","_YBRQ__S","_YBWZ__WD","_YBWZ__JD","_YBWZ__JDDW",
                    "_YBWZ__MC","SJLY","SJGXRQ", "_SJGXRQ__N","_SJGXRQ__Y","_SJGXRQ__R","_SJGXRQ__S","_SJGXRQ__F","_SJGXRQ__M"));
        }else if (apiName.equals("TQYBDT") || apiName.equals("TQYB15T")){
            return new ArrayList<>(Arrays.asList("YBNR","ID","_YBRQ__MC","_YBRQ__N","_YBRQ__Y","_YBRQ__R","_YBWZ__WD","_YBWZ__JD","_YBWZ__JDDW",
                    "_YBWZ__MC","SJLY","SJGXRQ", "_SJGXRQ__N","_SJGXRQ__Y","_SJGXRQ__R","_SJGXRQ__S","_SJGXRQ__F","_SJGXRQ__M"));
        }else{
            System.out.println("请输入正确的接口名");
            return new ArrayList<>();
        }
    }

}
