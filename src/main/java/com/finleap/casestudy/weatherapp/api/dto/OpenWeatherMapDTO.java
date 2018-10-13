package com.finleap.casestudy.weatherapp.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherMapDTO {

    @JsonProperty("cod")
    int code;
    String message;
    @JsonProperty("list")
    List<ForcastWeather> forcastWeathers;
    @JsonProperty("city")
    ForcastWeatherCity forcastWeatherCity;

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ForcastWeather {
        @JsonProperty("main")
        ForcastWeatherMain forcastWeatherMain;
        @JsonProperty("dt_txt")
        String forcastWeatherDateTime;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ForcastWeatherMain {
        @JsonProperty("temp")
        int temperature;
        int pressure;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ForcastWeatherCity {
        @JsonProperty("name")
        String city;
    }

}
