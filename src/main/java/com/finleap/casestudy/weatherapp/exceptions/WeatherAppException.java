package com.finleap.casestudy.weatherapp.exceptions;

import org.springframework.http.HttpStatus;

public class WeatherAppException extends RuntimeException{

    private HttpStatus code;

    public WeatherAppException(String msg, HttpStatus code) {
        super(msg);
        this.code = code;
    }

    public HttpStatus getCode(){
        return code;
    }
}
