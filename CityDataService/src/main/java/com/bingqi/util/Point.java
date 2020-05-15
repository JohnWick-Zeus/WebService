package com.bingqi.util;

/**
 * 存储经纬度的类
 * written by zhanggong
 *  */
public class Point {
    private double x;
    private double y;

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public Point(double x, double y){
        super();
        this.x=x;
        this.y=y;
    }

    @Override
    public String toString(){
        return String.valueOf(x)+","+String.valueOf(y);
    }
}
