package com.bingqi.component;

import com.bingqi.util.DataProcessUtil;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

@Component
public class CsvWriter {
    private DataProcessUtil dp = new DataProcessUtil();

    /**
     * 数组转换成csv
     * @param data 接口数据
     * @param path csv存储地址
     */
    public void Array2CSV(ArrayList<ArrayList<String>> data, ArrayList<String> title, String path)
    {
        try {
            File file = new File(path); // CSV数据文件
            // 追写附加！！！
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            // 写入title
            if (title != null){
                for (String str: title) {
                    bw.write(dp.fieldContentFormat(str));
                    bw.write(",");
                }
                bw.write("\n");
            }
            // 写入内容
            if (data != null && data.size() > 0){
                // 要注意：Java读取csv文件时默认是按照英文逗号分割的，如果字符串里面含有
                for (ArrayList<String> lineData : data) {
                    for (String str: lineData) {
                        bw.write(dp.fieldContentFormat(str));
                        bw.write(",");
                    }
                    bw.write("\n");
                }
                bw.flush();
                bw.close();
                System.out.println("数据写入CSV成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
