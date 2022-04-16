package com.example.thirdweekhomeworkahmetozcan.exceptions;

import lombok.Getter;
import lombok.Setter;

/*
 * It is using for Custom exception model
 */

@Getter
@Setter
public class WeatherApiError {
    public String message;
    public boolean isOk;

    public WeatherApiError(String message, boolean isOk) {
        this.message = message;
        this.isOk = isOk;
    }
}

