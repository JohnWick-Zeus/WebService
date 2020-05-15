package com.bingqi.util;

public class City implements Comparable<City>{
    private String MC;
    private String start;
    private String end;
    private int CSJJL;
    private int ZBCSPX;

    public String getMC() {
        return MC;
    }
    public void setMC(String MC) {
        this.MC = MC;
    }

    public String getStart() {
        return start;
    }
    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }
    public void setEnd(String end) {
        this.end = end;
    }

    public int getCSJJL() {
        return CSJJL;
    }
    public void setCSJJL(int CSJJL) {
        this.CSJJL = CSJJL;
    }

    public int getZBCSPX() {
        return ZBCSPX;
    }
    public void setZBCSPX(int ZBCSPX) {
        this.ZBCSPX = ZBCSPX;
    }

    public City(String MC, String start, String end, int CSJJL, int ZBCSPX){
        this.MC = MC;
        this.start = start;
        this.end = end;
        this.CSJJL = CSJJL;
        this.ZBCSPX = ZBCSPX;
    }


    @Override
    public int compareTo(City city) {           //重写Comparable接口的compareTo方法，
         return this.CSJJL - city.getCSJJL();// 根据年龄升序排列，降序修改相减顺序即可
         }
}
