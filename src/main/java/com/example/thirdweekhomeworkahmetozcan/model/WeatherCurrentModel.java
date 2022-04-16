package com.example.thirdweekhomeworkahmetozcan.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class WeatherCurrentModel {
    private Date time;
    private double temp;
    private double feel;
    private double humidity;
    private String weather;

    @Override
    public String toString() {
        return "WeatherCurrentModel{" +
                "time=" + time +
                ", temp=" + temp +
                ", feel=" + feel +
                ", humidity=" + humidity +
                ", weather='" + weather + '\'' +
                '}';
    }
}
