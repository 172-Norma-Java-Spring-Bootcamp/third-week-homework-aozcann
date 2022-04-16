package com.example.thirdweekhomeworkahmetozcan.controller;

import com.example.thirdweekhomeworkahmetozcan.model.request.WeatherApiRequestModel;
import com.example.thirdweekhomeworkahmetozcan.model.response.ControllerResponse;
import com.example.thirdweekhomeworkahmetozcan.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@Slf4j
@Validated
@RequestMapping(path = "/api")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<ControllerResponse> getWeatherForecastByLocation(@RequestBody @Valid WeatherApiRequestModel weatherApiRequestModel) {
        log.info("Weather location info: type -> {}, country -> {}, city -> {} ", weatherApiRequestModel.getType()  ,weatherApiRequestModel.getCity(),weatherApiRequestModel.getCountry());

        ControllerResponse response = weatherService.getWeatherForecastByLocation(weatherApiRequestModel.getType(), weatherApiRequestModel.getCountry(),weatherApiRequestModel.getCity());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
