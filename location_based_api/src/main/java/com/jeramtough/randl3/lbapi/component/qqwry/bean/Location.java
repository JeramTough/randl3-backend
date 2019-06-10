package com.jeramtough.randl3.lbapi.component.qqwry.bean;

/**
 * @Description:位置
 * @author:difeng
 * @date:2016年12月13日
 */
public class Location {

    private String city;

    private String operator;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "Location [city=" + city + ", operator=" + operator + "]";
    }

}

