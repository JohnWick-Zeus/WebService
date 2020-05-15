package com.bingqi.test;

import com.bingqi.component.CsvWriter;
import com.bingqi.util.City;
import com.bingqi.service.WeatherCityService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCityService {

    private WeatherCityService service;
    private CsvWriter writer;
    private String nodeFilePath = "/node_ZGCS.csv";


    //编写APIs的测试类
    @Before
    public void setUp() throws Exception{
        System.out.println("-----start-----");
        service = new WeatherCityService();
        writer = new CsvWriter();
    }

    @After
    public void tearDown() throws Exception{
        System.out.println("-----Done-----");
    }

    @Test
    public void TestReadExcelFile() {
        ArrayList<ArrayList<String>> data = service.readExcelFile("src/main/resources/city.xlsx");
        System.out.println(data.size());
        System.out.println();
    }

    @Test
    public void TestArray2Csv(){
        ArrayList<ArrayList<String>> data = service.readExcelFile("/city.xlsx");
        ArrayList<ArrayList<String>> transData = service.dataTransfer(data);
        ArrayList<String> title = new ArrayList<>(Arrays.asList("type","ID","MC","SF","CSM","_JWDWZZB__ID","_JWDWZZB__WD","_JWDWZZB__JD"));
        writer.Array2CSV(transData, title, "/Users/zhanggong/Desktop/node_ZGCS.csv");
    }

    @Test
    public void TestReadCsvFile(){
        System.out.println(service.readCsvFile(nodeFilePath));
    }

    @Test
    public void TestGetFiveCity(){
        ArrayList<ArrayList<String>> allCity = service.readCsvFile(nodeFilePath);
        List<City> fiveCity = service.getFiveCity(allCity.get(0), allCity);
        for (City city: fiveCity){
            System.out.println(city.getStart()+ "\t" + city.getEnd() +"\t"+ city.getCSJJL() + "\t" + city.getZBCSPX());
        }
        System.out.println(fiveCity.size());
    }

    @Test
    public void TestGetFiveCityData(){
        ArrayList<ArrayList<String>> allCity = service.readCsvFile(nodeFilePath);
        ArrayList<ArrayList<String>> data = service.getFiveCityData(allCity);
        System.out.println(service.getTitleStr());
        System.out.println(data);
    }

    @Test
    public void TestCity2CSV(){
        ArrayList<ArrayList<String>> allCity = service.readCsvFile(nodeFilePath);
        ArrayList<ArrayList<String>> data = service.getFiveCityData(allCity);
        ArrayList<String> title = service.getTitleStr();
        writer.Array2CSV(data, title, "/Users/zhanggong/Desktop/relation_ZBCSGX.csv");
    }
}
