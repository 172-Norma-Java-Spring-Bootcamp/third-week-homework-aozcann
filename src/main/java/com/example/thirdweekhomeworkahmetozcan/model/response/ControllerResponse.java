package com.example.thirdweekhomeworkahmetozcan.model.response;

import lombok.Getter;
import lombok.Setter;

/*
* This class control the all weather type model.
* It allows us to return data in different model types.
*/

@Getter
@Setter
public class ControllerResponse<T> {

    private String status;
    private String message;
    private T data;

}