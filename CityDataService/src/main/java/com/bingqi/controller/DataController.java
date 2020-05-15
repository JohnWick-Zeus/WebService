package com.bingqi.controller;

import com.alibaba.fastjson.JSONObject;
import com.bingqi.component.CsvPath;
import com.bingqi.component.CsvWriter;
import com.bingqi.service.WeatherCityService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * written by zhanggong
 * date:2020-04-24
 */
@RestController
@RequestMapping("/api")
@Api(value = "/api")
// 表示整个Controller的方法返回值都是json或json对象
public class DataController {

    @GetMapping("/test")
    @ApiOperation(value = "测试接口", notes="无参数测试接口")
    public String Hello(){
        return "Test-Controller";
    }

    //处理请求地址映射的注解
    @Autowired
    private CsvPath path;
    @Autowired
    private WeatherCityService service;
    @Autowired
    private CsvWriter writer;

    @ApiOperation(value="node_ZGCS.csv生成接口")
    @RequestMapping(value = "/getZGCS", method = RequestMethod.GET)
    // 支持跨域注解
    @CrossOrigin(origins = "*", allowCredentials = "true", maxAge = 3600, methods = RequestMethod.GET, allowedHeaders="*")
    public String getZGCS(){
        try{
            ArrayList<ArrayList<String>> data = service.readExcelFile("/city.xlsx");
            ArrayList<ArrayList<String>> transData = service.dataTransfer(data);
            ArrayList<String> title = new ArrayList<>(Arrays.asList("type","ID","MC","SF","CSM","_JWDWZZB__ID","_JWDWZZB__WD","_JWDWZZB__JD"));
            writer.Array2CSV(transData, title, path.ZGCS_PATH);

        }catch (Exception e){
            e.printStackTrace();
        }
        return "文件写入csv失败！";
    }

    @ApiOperation(value="relation_ZBCSGX.csv生成接口")
    @RequestMapping(value = "/getZBCSGX", method = RequestMethod.GET)
    // 支持跨域注解
    @CrossOrigin(origins = "*", allowCredentials = "true", maxAge = 3600, methods = RequestMethod.GET, allowedHeaders="*")
    public String getZBCSGX(){
        try{
            ArrayList<ArrayList<String>> allCity = service.readCsvFile("/node_ZGCS.csv");
            ArrayList<ArrayList<String>> data = service.getFiveCityData(allCity);
            ArrayList<String> title = service.getTitleStr();
            writer.Array2CSV(data, title, path.ZBCSGX_PATH);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "文件写入csv成功！";
    }

}
