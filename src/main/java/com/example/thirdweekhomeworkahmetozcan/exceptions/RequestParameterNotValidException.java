package com.example.thirdweekhomeworkahmetozcan.exceptions;


public class RequestParameterNotValidException extends WeatherApiControllerException {

    public RequestParameterNotValidException() {
        super();
    }

    public RequestParameterNotValidException(String message) {
        super(message);
    }

    public RequestParameterNotValidException(String message, Throwable cause) {
        super(message, cause);
    }
}
