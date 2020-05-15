package com.bingqi.test;

import com.bingqi.service.DataProcessService;
import com.bingqi.util.DataProcessUtil;
import com.bingqi.util.Point;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * 测试类
 * written by 张弓
 */
public class TestDataProcessService {
    private DataProcessService dpService;
    private DataProcessUtil dpUtil;
    private String[] strings = {"22.799", "101.578", "22.599", "101.777"};
    private double[] list = {22.799, 101.578, 22.599, 101.677};
    private double[] list1 = {22.889, 101.666, 22.888, 101.777};

    //编写DataProcess的测试类
    @Before
    public void setUp() throws Exception {
        dpService = new DataProcessService();
        dpUtil = new DataProcessUtil();

        System.out.println("-----start-----");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("-----Done-----");
    }

    @Test
    public void TestStr2Double() {
        double[] list = dpUtil.Str2Double(strings);
        System.out.println(Arrays.toString(list));
    }

    @Test
    public void TestGetCoordinate() {
        Vector<Point> points = dpUtil.GetCoordinate(list);
        //输出存入的坐标
        for (Point p : points) {
            System.out.print(p + "\t");
        }
        System.out.println();
    }

    @Test
    public void TestGetMethod() throws Exception {
        String[] names = dpUtil.GetMethod(Class.forName("com.bingqi.service.AliWeatherService"));
        System.out.println(Arrays.asList(names));
    }

    @Test
    public void TestGetApiValue() throws Exception {
//        String[] names = service.GetMethod(Class.forName("com.bingqi.service.AliWeatherService"));
        String name = "Forecast15Days";
        String[] params = {"22.799", "101.578"};
        System.out.println(dpUtil.GetApiValue(name, params));
    }

    @Test
    public void TestPoint2Data1() {
        Vector<Point> points = dpUtil.GetCoordinate(list);
        dpService.Point2Forecast15DaysData(points);
    }

    @Test
    public void TestPoint2Data2() {
        Vector<Point> points = dpUtil.GetCoordinate(list);
        dpService.Point2Forecast24HoursData(points);
    }

    @Test
    public void TestPoint2Data3() {
        Vector<Point> points = dpUtil.GetCoordinate(list);
        dpService.Point2ConditionData(points);
    }

    @Test
    public void TestForecast15Days()throws Exception{
        String[] strings = new String[]{"22.1", "100.1", "23.0", "101.0"};
        Vector<Point> points = dpUtil.GetCoordinate(dpUtil.Str2Double(strings));
        ArrayList<ArrayList<String>> data = dpService.Point2Forecast15DaysData(points);
        dpService.Array2CSV(data, "/Users/zhanggong/Desktop/node_TQYB15T.csv");
    }

    @Test
    public void TestForecast24Hours()throws Exception{
        String[] strings = new String[]{"22.1", "100.1", "23.0", "101.0"};
        Vector<Point> points = dpUtil.GetCoordinate(dpUtil.Str2Double(strings));
        ArrayList<ArrayList<String>> data = dpService.Point2Forecast24HoursData(points);
        dpService.Array2CSV(data, "/Users/zhanggong/Desktop/node_TQYBDTZXS.csv");
    }

    @Test
    public void TestCondition()throws Exception{
        String[] strings = new String[]{"22.1", "100.1", "23.0", "101.0"};
        Vector<Point> points = dpUtil.GetCoordinate(dpUtil.Str2Double(strings));
        ArrayList<ArrayList<String>> data = dpService.Point2ConditionData(points);
        dpService.Array2CSV(data, "/Users/zhanggong/Desktop/node_TQYBDT.csv");
    }

}