package com.example.thirdweekhomeworkahmetozcan.service;

import com.example.thirdweekhomeworkahmetozcan.model.enums.WeatherType;
import com.example.thirdweekhomeworkahmetozcan.model.response.ControllerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherApiService weatherApiService;

    public ControllerResponse getWeatherForecastByLocation(WeatherType type, String country, String city) {
        log.info("getWeatherForecastByLocation will be called");
        return weatherApiService.getCurrentAndForecastWeatherData(type, country, city);
    }

}
