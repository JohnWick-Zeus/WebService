package com.bingqi.service;

import com.bingqi.component.PoiUtil;
import com.bingqi.util.City;
import com.bingqi.util.LocationUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import static com.bingqi.component.PoiUtil.getCellFormatValue;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 天气数据提取服务
 * written by zhanggong
 * datetime：2010/04/22
 */
@Service
public class WeatherCityService {

    private LocationUtils location = new LocationUtils();
    private PoiUtil poi = new PoiUtil();

    /**
     * 读取excel文件
     * @param filePath 文件路径
     * @return 数组
     */
    public ArrayList<ArrayList<String>> readExcelFile(String filePath) {
        Sheet sheet;
        Row row;
        ArrayList<ArrayList<String>> arrays = new ArrayList<>();
        String cellData;
//        String[] columns = {"Fid","城市名称","省份","二级城市","经度","纬度"};
        Workbook wb = poi.readExcel(filePath);
        if(wb != null){
            //用来存放表中数据
            arrays = new ArrayList<>();
            //获取第一个sheet
            sheet = wb.getSheetAt(0);
            //获取最大行数 也会包括空行！！！
//            int rownum = sheet.getPhysicalNumberOfRows();
            int rownum = 388;
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i<rownum; i++) {
                ArrayList<String> array = new ArrayList<>();
                row = sheet.getRow(i);
                if(row !=null){
                    for (int j=0;j<colnum;j++){
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        array.add(cellData);
                    }
                }else{
                    break;
                }
                arrays.add(array);
            }
        }
        return arrays;
    }

    /**
     * 将表格数据转化成node_ZGCS.csv数据
     * @param data 原始xlsx表格数据
     * @return 数组
     */
    public ArrayList<ArrayList<String>> dataTransfer(ArrayList<ArrayList<String>> data){
        ArrayList<ArrayList<String>> transData = new ArrayList<>();
        if(data != null) {
            try {
                ArrayList<String> lineData; // 一行数据
                for (ArrayList<String> array: data) {
                    // YBNR：预报内容
                    String type = "ZGCS";

                    // uuid码：
                    String ID = UUID.randomUUID().toString();

                    // MC:省份+城市
                    String MC = array.get(2) + array.get(3);

                    // SF:省份
                    String SF = array.get(2);

                    // CSM:城市
                    String CSM = array.get(3);

                    // _JWDWZZB__ID:城市编号编号
                    String _JWDWZZB__ID = ID;

                    // _JWDWZZB__WD:纬度
                    String _JWDWZZB__WD = array.get(5);

                    // _JWDWZZB__WD:经度
                    String _JWDWZZB__JD = array.get(4);

                    lineData = new ArrayList<>(Arrays.asList(type, ID, MC, SF, CSM, _JWDWZZB__ID, _JWDWZZB__WD, _JWDWZZB__JD));
                    transData.add(lineData);
                }
                System.out.println("数据生成成功！");
                return transData;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return transData;
    }

    /**
     * 遍历所有城市得到前五的数据，得到
     * @param allCity 所有城市数据
     */
    public ArrayList<ArrayList<String>> getFiveCityData(ArrayList<ArrayList<String>> allCity){
        ArrayList<ArrayList<String>> finalData = new ArrayList<>();
        if (allCity == null ){
            System.out.println("城市信息缺失！");
            return finalData;
        }
        try{
            for (ArrayList<String> oneCity: allCity){
                List<City> fiveCity = getFiveCity(oneCity, allCity);
                for (City city : fiveCity) {
                    String type = "ZBCSGX";
                    String ID = UUID.randomUUID().toString();
                    String MC = city.getMC();
                    String start = String.valueOf(city.getStart());
                    String end = String.valueOf(city.getEnd());
                    String startType = "ZGCS";
                    String endType = "ZGCS";
                    String CSJJL = String.valueOf(city.getCSJJL());
                    String ZBCSPX = String.valueOf(city.getZBCSPX());

                    ArrayList<String> list = new ArrayList<>(Arrays.asList(type, ID, MC, start, end, startType, endType, CSJJL, ZBCSPX));
                    finalData.add(list);
                }
            }
            return finalData;
        }catch (Exception e){
            e.printStackTrace();
        }
        return finalData;
    }

    /**
     * title
     * @return ArrayList<String>
     */
    public ArrayList<String> getTitleStr(){
        return new ArrayList<>(Arrays.asList("type","ID","MC","start","end","startType","endType","CSJJL","ZBCSPX"));
    }

    /**
     * 提供一个城市与全国所有城市距离排名前五（小->大）
     * @param oneCity 单个城市数据
     * @param allCity 所有城市数据
     * @return 前五城市数据
     */
    public List<City> getFiveCity(ArrayList<String> oneCity, ArrayList<ArrayList<String>> allCity){
        ArrayList<City> oneCityData = new ArrayList<>();
        if (oneCity != null & allCity != null){

            for (ArrayList<String> strings : allCity) {
                int distance = (int) location.getDistance(Double.parseDouble(oneCity.get(2)), Double.parseDouble(oneCity.get(3)),
                        Double.parseDouble(strings.get(2)), Double.parseDouble(strings.get(3)));
                City city = new City(oneCity.get(0), oneCity.get(1), strings.get(1), distance, 0);
                oneCityData.add(city);
            }
            //重写Comparable接口的compareTo方法，
            // 根据年龄升序排列，降序修改相减顺序即可
            Collections.sort(oneCityData); // list参数类型相对复杂的排序
            List<City> fiveCity = oneCityData.subList(1,6); // 这里取了前五
            for (int i=0; i<fiveCity.size(); i++){
                fiveCity.get(i).setZBCSPX(i+1);
            }
            return fiveCity;
        }
        return new ArrayList<>();
    }

    /**
     * 读csv文件
     * @param path 文件路径
     * @return 数组保存数据
     */
    public ArrayList<ArrayList<String>> readCsvFile(String path){
        ArrayList<ArrayList<String>> allData = new ArrayList<>();
        try{
            InputStream is = this.getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            br.readLine(); // 如果有标题
            String line;
            String[] strs;
            while((line=br.readLine()) != null){
                ArrayList<String> lineData = new ArrayList<>();
                strs = line.split(",");
                if (strs.length < 8){
                    System.out.println("数据缺失/读取失败");

                }
                String[] strs1 = {strs[2].replace("\"", ""), strs[5].replace("\"", ""),
                        strs[6].replace("\"", ""), strs[7].replace("\"", "")};
                lineData.addAll(Arrays.asList(strs1));
                allData.add(lineData);
            }
            return allData;

        }catch (IOException e){
            e.printStackTrace();
        }
        return allData;
    }



}
