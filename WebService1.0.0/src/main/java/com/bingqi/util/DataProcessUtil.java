package com.bingqi.util;

import com.bingqi.service.AliWeatherService;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * 数据处理工具类
 * written by 张弓
 */
public class DataProcessUtil {
    /**
     * 去掉字符串中的符号
     */
    public String DelQuota(String str)
    {
        String result = str;
        String[] strQuota = { "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "`", ";", "'", ",", "/", ":", "/,", "<", ">", "?" }; // 去掉了"."
        for (String s : strQuota) {
            if (result.contains(s))
                result = result.replace(s, "");
        }
        return result;
    }

    /**
     * 写入csv文件时，对字段内容进行格式化
     * 1，英文逗号处理
     * 2，英文双引号处理
     * @param content 待处理数据
     * @return 处理之后的String
     */
    public String fieldContentFormat(Object content) {

        if (content == null) {
            return "";
        }

        String notNullContent = content.toString();
        if (notNullContent.contains("\"")) {
            // 处理英文双引号
            notNullContent = notNullContent.replaceAll("\"", "\"\"");
        }

        // 处理英文逗号
        return "\"" + notNullContent + "\"";
    }

    /**
     * String转化为double
     */
    public double[] Str2Double(String[] strings) {
        int l = strings.length;
        if (l==4){
            try {
                double[] list = new double[4];
                for (int i=0; i<4; i++) {
                    list[i] = Double.parseDouble(strings[i]);
                }
                return list;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return new double[4];
    }

    /**
     * 根据两点确定矩形范围，按照0.1度（10km）进行取点存储。
     * @param list double数组，坐标点数据。
     * @return 矩形范围的点的集合。
     */
    public Vector<Point> GetCoordinate(double[] list){
        BigDecimal b;
        double min_lat = (double)(Math.round(Math.min(list[0], list[2])*10))/10; //截取小数点后一位
        double max_lat = (double)(Math.round(Math.max(list[0], list[2])*10))/10;
        double min_lon = (double)(Math.round(Math.min(list[1], list[3])*10))/10;
        double max_lon = (double)(Math.round(Math.max(list[1], list[3])*10))/10;
        long m = (Math.round((max_lat-min_lat)*10)+1);
        long n = (Math.round((max_lon-min_lon)*10)+1);
        Vector<Point> points = new Vector<>();
        if (m > 1 | n > 1){
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    double x = min_lat+i*0.1;
                    double y = min_lon+j*0.1;
                    Point p = new Point(Double.parseDouble(String.format("%.1f", x)), Double.parseDouble(String.format("%.1f", y)));
                    points.add(p);
                }
            }
            return points;
        }else{
            points.add(new Point(min_lat, min_lon));
            return points;
        }
    }

    /**
     * 获取一个类的所有public方法
     * @param type 类型
     * @return names 方法名的String数组
     */
    public String[] GetMethod(Class type) throws Exception {
        Method[] methods = type.getDeclaredMethods();//取得该类的所有方法
        String[] names = new String[methods.length];
        for (int i=0; i<methods.length; i++) {
            String method_name = methods[i].getName();//取得该方法的名
            names[i] = method_name;
        }
        return names;
    }

    /**
     * 遍历运行某类中的public方法并返回String结果
     * @param name 方法名 "Forecast15Days"
     * @param params 默认这个方法的输入参数都是一样的
     * @return 该方法返回的原始数据
     */
    public String GetApiValue(String name, String[] params)throws Exception{
        AliWeatherService weatherService = new AliWeatherService();
        DataProcessUtil dp = new DataProcessUtil();
        // 获取所有的方法名字
        String[] names = dp.GetMethod(Class.forName("com.bingqi.service.AliWeatherService"));
        Map<String, String[]> methods = new HashMap<>();

        assert params != null;
        if (name != null & params.length==2 & Arrays.asList(names).contains(name)){
            methods.put(name, params);
            for (Map.Entry<String, String[]> item : methods.entrySet()) {
                try {
                    //getMethod第一个参数是函数名,后面的参数都是针对于目标方法的参数类型,没有参数就传null
                    Method method = AliWeatherService.class.getMethod(item.getKey(), String[].class);
                    //invoke第一个参数是一个对象的实例,后面跟进一堆参数列表,没有参数就传null
                    return (String)method.invoke(weatherService, (Object) item.getValue());
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return "";
    }

    public static void addCloumn(List<String> pList, String filePath) throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader(filePath));
        String lineStr = "";
        int rowNumber = 0;
        StringBuilder nContent = new StringBuilder();
        while((lineStr = bufReader.readLine()) != null){
            String addValue = "";
            if(rowNumber < pList.size()){
                addValue = pList.get(rowNumber);
            }
            if(lineStr.endsWith(",")){
                nContent.append(lineStr).append("\"").append(addValue).append("\"");
            }else{
                nContent.append(lineStr).append(",\"").append(addValue).append("\"");
            }
            rowNumber++;
            nContent.append("\r\n");
        }
        bufReader.close();
        FileOutputStream fileOs = new FileOutputStream(new File(filePath), false);
        fileOs.write(nContent.toString().getBytes());
        fileOs.close();
    }
}
