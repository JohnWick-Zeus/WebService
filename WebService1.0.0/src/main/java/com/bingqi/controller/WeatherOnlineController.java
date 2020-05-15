package com.bingqi.controller;

import com.bingqi.service.AliWeatherService;
import com.bingqi.service.DataProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/weather")
@Api(value = "/weather")
// 表示整个Controller的方法返回值都是json或json对象
public class WeatherOnlineController {

    @GetMapping("/test")
    @ApiOperation(value = "测试接口", notes="无参数测试接口")
    public String Hello(){
        return "Test-Controller";
    }

    //处理请求地址映射的注解
    @Autowired
    private AliWeatherService weatherService;

    @ApiOperation(value = "天气预报当天逐小时", notes="forecast24hours接口测试") //dataType的类型可以是String和Int，指的是单元素类型或者集合中元素类型
    @ApiImplicitParam(name="params", value="经纬度", required = true, dataType = "String", allowMultiple = true) // allowMultiple表示允许多个值，也就是是数组格式的参数；
    @PostMapping(value = "/forecast24hours")
    public String Forecast24Hours(@RequestParam(name="params") String[] params){
        return weatherService.Forecast24Hours(params);
    }

    @ApiOperation(value = "天气预报15天", notes="forecast15days接口测试")
    @ApiImplicitParam(name="params", value="经纬度", required = true, dataType = "String", allowMultiple = true)
    @PostMapping(value = "/forecast15days")
    public String Forecast15Days(@RequestParam(name="params") String[] params){
        return weatherService.Forecast15Days(params);
    }

    @ApiOperation(value = "天气预报当天", notes="condition接口测试")
    @ApiImplicitParam(name="params", value="经纬度", required = true, dataType = "String", allowMultiple = true)
    @PostMapping(value = "/condition")
    public String Condition(@RequestParam(name="params") String[] params){
        return weatherService.Condition(params);
    }

    @ApiOperation(value="短时预报（2小时）", notes="ShortForecast接口测试")
    @ApiImplicitParam(name="params", value="经纬度", required = true, dataType = "String", allowMultiple = true)
    @PutMapping(value = "/shortforecast")
    public String ShortForecast(@RequestParam(name="params") String[] params) throws Exception{
        return weatherService.ShortForecast(params);
    }

    @ApiOperation(value="天气预警", notes="Alert接口测试")
    @ApiImplicitParam(name="params", value="经纬度", required = true, dataType = "String", allowMultiple = true)
    @PutMapping(value = "/alert")
    public String Alert(@RequestParam(name="params") String[] params) throws Exception{
        return weatherService.Alert(params);
    }

    @ApiOperation(value="生活指数当天", notes="Index接口测试")
    @ApiImplicitParam(name="params", value="经纬度", required = true, dataType = "String", allowMultiple = true)
    @PutMapping(value = "/index")
    public String Index(@RequestParam(name="params") String[] params) throws Exception{
        return weatherService.Index(params);
    }

    @ApiOperation(value="空气质量指数预报（6天）", notes="AqiForecast5Days接口测试")
    @ApiImplicitParam(name="params", value="经纬度", required = true, dataType = "String", allowMultiple = true)
    @PutMapping(value = "/aqiforecast5days")
    public String AqiForecast5Days(@RequestParam(name="params") String[] params) throws Exception{
        return weatherService.AqiForecast5Days(params);
    }

    @ApiOperation(value="空气质量指数当天", notes="Aqi接口测试")
    @ApiImplicitParam(name="params", value="经纬度", required = true, dataType = "String", allowMultiple = true)
    @PutMapping(value = "/aqi")
    public String Aqi(@RequestParam(name="params") String[] params) throws Exception{
        return weatherService.Aqi(params);
    }

    @ApiOperation(value="限行数据", notes="Limit接口测试")
    @ApiImplicitParam(name="params", value="经纬度", required = true, dataType = "String", allowMultiple = true)
    @PutMapping(value = "/limit")
    public String Limit(@RequestParam(name="params") String[] params) throws Exception{
        return weatherService.Limit(params);
    }

    @ApiOperation(value="天气预测数据写入CSV文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name="lat1", value="A纬度", required = true, dataType = "String",paramType = "path"),
            @ApiImplicitParam(name="lon1", value="A经度", required = true, dataType = "String",paramType = "path"),
            @ApiImplicitParam(name="lat2", value="C纬度", required = true, dataType = "String",paramType = "path"),
            @ApiImplicitParam(name="lon2", value="C经度", required = true, dataType = "String",paramType = "path")})
    @RequestMapping(value = "/weatherforecast&lat1={lat1}&lon1={lon1}&lat2={lat2}&lon2={lon2}", method = RequestMethod.GET)
    public String WeatherForecast(@PathVariable String lat1, @PathVariable String lon1, @PathVariable String lat2, @PathVariable String lon2)throws Exception{
        return "接口调用成功！";
    }

//    //多参数接口测试
//    @ApiOperation(value = "空气质量指数当天", notes="aqi接口测试")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name="lat", value="纬度", required = true, dataType = "String",paramType = "path"),
//            @ApiImplicitParam(name="lon", value="经度", required = true, dataType = "String",paramType = "path")})
//    @GetMapping(value = "/aqi&lat={lat}&lon={lon}")
//    public String Aqi(@PathVariable(name="lat") String lat, @PathVariable(name="lon") String lon){
//        return weatherService.Aqi(lat, lon);
//    }
}
