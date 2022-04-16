package com.example.thirdweekhomeworkahmetozcan.exceptions;

public class WeatherApiControllerException extends Exception{

    public WeatherApiControllerException() {
    }

    public WeatherApiControllerException(String message) {
        super(message);
    }

    public WeatherApiControllerException(String message, Throwable cause) {
        super(message, cause);
    }
}
