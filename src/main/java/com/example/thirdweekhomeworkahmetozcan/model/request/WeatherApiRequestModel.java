package com.example.thirdweekhomeworkahmetozcan.model.request;

import com.example.thirdweekhomeworkahmetozcan.model.enums.WeatherType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder(builderMethodName = "of")
public class WeatherApiRequestModel {

    private WeatherType type;
    @NotBlank(message = "Country can't not be null.You must submit the abbreviated name of your country like 'TR'.")
    private String country;
    @NotBlank(message = "City can't not be null")
    private String city;
}
