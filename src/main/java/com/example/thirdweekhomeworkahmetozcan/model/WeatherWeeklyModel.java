package com.example.thirdweekhomeworkahmetozcan.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class WeatherWeeklyModel {
    private List<WeatherDailyModel> weatherWeeklyModel;

    @Override
    public String toString() {
        return "WeatherWeeklyModel{" +
                "weatherWeeklyModel=" + weatherWeeklyModel +
                '}';
    }
}
