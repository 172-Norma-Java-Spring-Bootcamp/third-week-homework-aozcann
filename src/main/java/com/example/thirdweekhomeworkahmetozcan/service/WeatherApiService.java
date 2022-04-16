package com.example.thirdweekhomeworkahmetozcan.service;

import com.example.thirdweekhomeworkahmetozcan.model.*;
import com.example.thirdweekhomeworkahmetozcan.model.enums.WeatherType;
import com.example.thirdweekhomeworkahmetozcan.model.response.ControllerResponse;
import com.example.thirdweekhomeworkahmetozcan.utils.ModelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherApiService {
    private static final String appid = "991f46ddd982fce0b6128342fd51a371";
    private final RestTemplate restTemplate;

    /*
    * Returns the location (lat,lon) of the given city name.
    */
    public CoordinationModel getCoordinatesByLocationName(String city, String country) {
        String locationUrl = "http://api.openweathermap.org/geo/1.0/direct";

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(locationUrl)
                .queryParam("q", city)
                .queryParam("limit", 10)
                .queryParam("appid", appid)
                .build();

        HttpEntity<String> requestEntity = new HttpEntity<>(null, getHeaders().getHeaders());

        // It makes an HTTP GET request according to the given url and parameters.
        ResponseEntity<List<CoordinationModel>> coordinateResponse = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                },
                new HashMap<>());

        // Get location model from outer weatherapi response body
        List<CoordinationModel> coordinationModelList = coordinateResponse.getBody();

        // Country name must be like 'TR','GB','US'..
        for (CoordinationModel coordinationModel : Objects.requireNonNull(coordinationModelList)) {
            if (country.equals(coordinationModel.getCountry())) {
                return coordinationModel;
            }
        }

        return null;
    }

    /*
    * It is returning the weather according weather type
     */
    public ControllerResponse getCurrentAndForecastWeatherData(WeatherType type, String country, String city) {

        // get lat lon information of given city and country
        CoordinationModel coordinationModel = getCoordinatesByLocationName(city, country);
        if (Objects.isNull(coordinationModel)) {
            log.error("Any coordinates not found with given {} and {}", city, country);
            ControllerResponse controllerResponse = new ControllerResponse();
            controllerResponse.setData(null);
            controllerResponse.setMessage("Any coordinates not found with given city and country.");
            controllerResponse.setStatus("400");
            return controllerResponse;
        }

        log.info("Get location info with city: {}, lat: {}, lon: {}", city, coordinationModel.getLat(), coordinationModel.getLon());

        String weatherUrl = "https://api.openweathermap.org/data/2.5/onecall?";

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(weatherUrl)
                .queryParam("lat", coordinationModel.getLat())
                .queryParam("lon", coordinationModel.getLon())
                .queryParam("appid", appid)
                .queryParam("units", "metric")
                .build();

        HttpEntity<String> requestEntity = new HttpEntity<>(null, getHeaders().getHeaders());

        // It makes an HTTP GET request according to the given url and parameters.
        ResponseEntity<WeatherModel> weatherResponse = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                requestEntity,
                WeatherModel.class,
                new HashMap<>());

        log.info("getCurrentAndForecastWeatherDataResponse -> {}", weatherResponse);

        // Get weather model from outer weatherapi response body
        WeatherModel weatherModel = weatherResponse.getBody();

        log.info("Weather model: {}", weatherModel.toString());

        /*
        * It Customized  data based on weather type from given weather model
         */
        if (type == WeatherType.DAILY) {
            WeatherDailyModel weatherDailyModel = ModelMapper.weatherModelToDailyModel(weatherModel.getDaily().get(0));
            log.info("Daily model: {}", weatherDailyModel.toString());

            ControllerResponse controllerResponse = new ControllerResponse();
            controllerResponse.setData(weatherDailyModel);
            controllerResponse.setMessage("Daily weather data send successfully.");
            controllerResponse.setStatus("200");
            return controllerResponse;

        } else if (type == WeatherType.CURRENT) {
            WeatherCurrentModel weatherCurrentModel = ModelMapper.weatherModelToCurrentModel(weatherModel.getCurrent());
            log.info("Current model: {}", weatherCurrentModel);

            ControllerResponse controllerResponse = new ControllerResponse();
            controllerResponse.setData(weatherCurrentModel);
            controllerResponse.setMessage("Current weather data send successfully.");
            controllerResponse.setStatus("200");
            return controllerResponse;

        } else if (type == WeatherType.HOURLY) {
            WeatherHourlyModel weatherHourlyModel = ModelMapper.weatherModelToHourlyModel(weatherModel.getHourly().get(0));
            log.info("Hourly model: {}", weatherHourlyModel.toString());

            ControllerResponse controllerResponse = new ControllerResponse();
            controllerResponse.setData(weatherHourlyModel);
            controllerResponse.setMessage("Hourly weather data send successfully.");
            controllerResponse.setStatus("200");
            return controllerResponse;

        } else if (type == WeatherType.WEEKLY) {
            WeatherWeeklyModel weatherWeeklyModel = ModelMapper.weatherModelToWeeklyModel(weatherModel.getDaily());
            log.info("Weekly model: {}", weatherWeeklyModel.toString());

            ControllerResponse controllerResponse = new ControllerResponse();
            controllerResponse.setData(weatherWeeklyModel);
            controllerResponse.setMessage("Weekly weather data send successfully.");
            controllerResponse.setStatus("200");
            return controllerResponse;

        } else {
            log.error("Weather type is wrong please check your type and select one of them 'CURRENT', 'HOURLY', 'DAILY' or 'WEEKLY'.. ");
            ControllerResponse controllerResponse = new ControllerResponse();
            controllerResponse.setData(null);
            controllerResponse.setMessage("Weather type is wrong.");
            controllerResponse.setStatus("400");
            return controllerResponse;
        }
    }

    /*
    * Generates headers model.
     */
    public HttpEntity<?> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }
}

