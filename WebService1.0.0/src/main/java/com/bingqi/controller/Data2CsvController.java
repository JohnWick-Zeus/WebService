package com.bingqi.controller;

import com.bingqi.service.DataProcessService;
import com.bingqi.util.Point;
import com.bingqi.util.DataProcessUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Vector;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/api")
@Api(value = "/api")
// 表示整个Controller的方法返回值都是json或json对象
public class Data2CsvController {

    @GetMapping("/test")
    @ApiOperation(value = "测试接口", notes="无参数测试接口")
    public String Hello(){
        return "Test-Controller";
    }

    //处理请求地址映射的注解
    @Autowired
    private DataProcessService dpService = new DataProcessService();
    private DataProcessUtil dpUtil = new DataProcessUtil();

    @ApiOperation(value="天气预报15天数据写入CSV文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name="lat1", value="A纬度", required = true, dataType = "String"),
            @ApiImplicitParam(name="lon1", value="A经度", required = true, dataType = "String"),
            @ApiImplicitParam(name="lat2", value="C纬度", required = true, dataType = "String"),
            @ApiImplicitParam(name="lon2", value="C经度", required = true, dataType = "String")})
    @RequestMapping(value = "/forecast15days", method = RequestMethod.POST)
    public String Forecast15Days(@RequestParam String lat1, @RequestParam String lon1, @RequestParam String lat2, @RequestParam String lon2)throws Exception{
        String[] strings = new String[]{lat1, lon1, lat2, lon2};
//        String[] strings = new String[]{"22.599", "101.579", "22.800", "101.777"};
        Vector<Point> points = dpUtil.GetCoordinate(dpUtil.Str2Double(strings));
        ArrayList<ArrayList<String>> data = dpService.Point2Forecast15DaysData(points);
        dpService.Array2CSV(data, "/Users/zhanggong/Desktop/node_TQYB15T.csv");
        return "接口调用成功！";
    }

    @ApiOperation(value="天气预报当天逐小时数据写入CSV文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name="lat1", value="A纬度", required = true, dataType = "String"),
            @ApiImplicitParam(name="lon1", value="A经度", required = true, dataType = "String"),
            @ApiImplicitParam(name="lat2", value="C纬度", required = true, dataType = "String"),
            @ApiImplicitParam(name="lon2", value="C经度", required = true, dataType = "String")})
    @RequestMapping(value = "/forecast24hours", method = RequestMethod.POST)
    public String Forecast24Hours(@RequestParam String lat1, @RequestParam String lon1, @RequestParam String lat2, @RequestParam String lon2)throws Exception{
        String[] strings = new String[]{lat1, lon1, lat2, lon2};
        Vector<Point> points = dpUtil.GetCoordinate(dpUtil.Str2Double(strings));
        ArrayList<ArrayList<String>> data = dpService.Point2Forecast24HoursData(points);
        dpService.Array2CSV(data, "/Users/zhanggong/Desktop/node_TQYBDTZXS.csv");
        return "接口调用成功！";
    }

    @ApiOperation(value="天气预报当天数据写入CSV文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name="lat1", value="A纬度", required = true, dataType = "String"),
            @ApiImplicitParam(name="lon1", value="A经度", required = true, dataType = "String"),
            @ApiImplicitParam(name="lat2", value="C纬度", required = true, dataType = "String"),
            @ApiImplicitParam(name="lon2", value="C经度", required = true, dataType = "String")})
    @RequestMapping(value = "/condition", method = RequestMethod.POST)
    public String Condition(@RequestParam String lat1, @RequestParam String lon1, @RequestParam String lat2, @RequestParam String lon2)throws Exception{
        String[] strings = new String[]{lat1, lon1, lat2, lon2};
        Vector<Point> points = dpUtil.GetCoordinate(dpUtil.Str2Double(strings));
        ArrayList<ArrayList<String>> data = dpService.Point2ConditionData(points);
        dpService.Array2CSV(data, "/Users/zhanggong/Desktop/node_TQYBDT.csv");
        return "接口调用成功！";
    }

}
