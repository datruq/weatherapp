package com.finleap.casestudy.weatherapp.config;

import com.finleap.casestudy.weatherapp.exceptions.WeatherAppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(WeatherAppException.class)
    public ResponseEntity<Map<String, String>> weatherAppException(final WeatherAppException e) {

        Map<String, String> data = new HashMap<>();
        data.put("message", e.getMessage());
        data.put("httpCode", e.getCode().getReasonPhrase());
        return new ResponseEntity<>(data, e.getCode());
    }

}
