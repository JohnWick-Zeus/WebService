package com.bingqi.util;

/**
 * 用来存数据点的类
 * written by 张弓
 */
public class Point {
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Point(double x, double y){
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return x+","+y;
    }
}
