package com.lialzm.bean;

import java.io.Serializable;

/**
 * Created by apple on 17/3/18.
 */
public class City implements Serializable{

    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                '}';
    }
}
