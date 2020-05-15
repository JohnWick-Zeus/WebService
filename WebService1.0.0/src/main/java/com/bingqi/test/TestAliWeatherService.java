package com.bingqi.test;

import com.bingqi.service.AliWeatherService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * 测试类
 * written by 张弓
 */
public class TestAliWeatherService {
    private AliWeatherService service;
    private String[] params = {"22.6", "101.7"};

    //编写APIs的测试类
    @Before
    public void setUp() throws Exception{
        service = new AliWeatherService();
        System.out.println("-----start-----");
    }

    @After
    public void tearDown() throws Exception{
        System.out.println("-----Done-----");
    }

    @Test
    public void TestShortForecast(){
        System.out.println(service.ShortForecast(params));
    }

    @Test
    public void TestForecast24Hours(){
        System.out.println(service.Forecast24Hours(params));
    }

    @Test
    public void TestAqiForecast5Days(){
        System.out.println(service.AqiForecast5Days(params));;
    }

    @Test
    public void TestAlert(){
        System.out.println(service.Alert(params));;
    }

    @Test
    public void TestIndex(){
        System.out.println(service.Index(params));;
    }

    @Test
    public void TestCondition(){
        System.out.println(service.Condition(params));;
    }

    @Test
    public void TestForecast15Days() throws IOException {
        System.out.println(service.Forecast15Days(params));;
    }

    @Test
    public void TestLimit(){
        System.out.println(service.Limit(params));;
    }

    @Test
    public void TestAqi(){
        System.out.println(service.Aqi(params));;
    }
}
