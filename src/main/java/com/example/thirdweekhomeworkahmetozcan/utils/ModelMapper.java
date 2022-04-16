package com.example.thirdweekhomeworkahmetozcan.utils;


import com.example.thirdweekhomeworkahmetozcan.model.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ModelMapper {
    private static Date timestampToDate(Integer time) {
        return new Date((long) time * 1000);
    }

    public static WeatherDailyModel weatherModelToDailyModel(WeatherModel.Daily daily) {
        WeatherDailyModel weatherDailyModel = new WeatherDailyModel();
        weatherDailyModel.setDay(timestampToDate(daily.getDt()));
        weatherDailyModel.setWeatherInfo(daily.getWeather().get(0).getDescription());
        weatherDailyModel.setHumidity(daily.getHumidity());
        weatherDailyModel.setFeel(daily.getFeels_like().getDay());
        weatherDailyModel.setTempDegree(daily.getTemp().getDay());
        return weatherDailyModel;
    }

    public static WeatherCurrentModel weatherModelToCurrentModel(WeatherModel.Current current) {
        WeatherCurrentModel weatherCurrentModel = new WeatherCurrentModel();
        weatherCurrentModel.setTime(timestampToDate(current.getDt()));
        weatherCurrentModel.setWeather(current.getWeather().get(0).getDescription());
        weatherCurrentModel.setFeel(current.getFeels_like());
        weatherCurrentModel.setHumidity(current.getHumidity());
        weatherCurrentModel.setTemp(current.getTemp());
        return weatherCurrentModel;
    }

    public static WeatherHourlyModel weatherModelToHourlyModel(WeatherModel.Hourly hourly) {
        WeatherHourlyModel weatherHourlyModel = new WeatherHourlyModel();
        weatherHourlyModel.setDay(timestampToDate(hourly.getDt()));
        weatherHourlyModel.setWeatherInfo(hourly.getWeather().get(0).getDescription());
        weatherHourlyModel.setFeel(hourly.getFeels_like());
        weatherHourlyModel.setHumidity(hourly.getHumidity());
        weatherHourlyModel.setTempDegree(hourly.getTemp());
        return weatherHourlyModel;
    }

    public static WeatherWeeklyModel weatherModelToWeeklyModel(List<WeatherModel.Daily> dailyList) {
        List<WeatherDailyModel> weatherDailyModelList = new ArrayList<>();
        for (WeatherModel.Daily daily : dailyList) {
            WeatherDailyModel weatherDailyModel = new WeatherDailyModel();
            weatherDailyModel.setDay(timestampToDate(daily.getDt()));
            weatherDailyModel.setWeatherInfo(daily.getWeather().get(0).getDescription());
            weatherDailyModel.setHumidity(daily.getHumidity());
            weatherDailyModel.setFeel(daily.getFeels_like().getDay());
            weatherDailyModel.setTempDegree(daily.getTemp().getDay());
            weatherDailyModelList.add(weatherDailyModel);
        }
        WeatherWeeklyModel weatherWeeklyModel = new WeatherWeeklyModel();
        weatherWeeklyModel.setWeatherWeeklyModel(weatherDailyModelList);
        return weatherWeeklyModel;

    }
}
