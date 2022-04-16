package com.example.thirdweekhomeworkahmetozcan.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WeatherHourlyModel {

    private Date day;
    private double humidity;
    private String weatherInfo;
    private double tempDegree;
    private double feel;

    @Override
    public String toString() {
        return "WeatherHourlyModel{" +
                "day=" + day +
                ", humidity=" + humidity +
                ", weatherInfo='" + weatherInfo + '\'' +
                ", tempDegree=" + tempDegree +
                ", feel=" + feel +
                '}';
    }
}
