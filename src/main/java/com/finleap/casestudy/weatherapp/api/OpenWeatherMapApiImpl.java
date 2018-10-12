package com.finleap.casestudy.weatherapp.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finleap.casestudy.weatherapp.api.dto.OpenWeatherMapDTO;
import com.finleap.casestudy.weatherapp.domain.gateway.WeatherAppService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class OpenWeatherMapApiImpl implements WeatherAppService {

    private static final String HTTP_ENTITY_PARAMETERS = "parameters";
    private static final String OPEN_WEATHER_MAP_CITY = "city";

    @Value("${x.api.key}")
    private String appKeyName;
    @Value("${open.weather.map.api.key}")
    private String appKeyValue;
    @Value("${open.weather.map.url}")
    private String openWeatherMapUrl;


    @Override
    public OpenWeatherMapDTO getReportFromCityForTheLastThreeHours(String city) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put(OPEN_WEATHER_MAP_CITY, city);
        HttpEntity<String> entity = setHttpHeaders(addHttpHeader(appKeyName, appKeyValue));
        return jsonStringToOpenWeatherMapDTO(restTemplate.exchange(
                openWeatherMapUrl, HttpMethod.GET, entity, String.class, params).getBody());
    }

    public OpenWeatherMapDTO jsonStringToOpenWeatherMapDTO(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, OpenWeatherMapDTO.class);
    }

    public HttpEntity<String> setHttpHeaders(HttpHeaders headers){
        return new HttpEntity<>(HTTP_ENTITY_PARAMETERS, headers);
    }

    public HttpHeaders addHttpHeader(String headerKey, String headerValue){
        HttpHeaders headers = new HttpHeaders();
        headers.add(headerKey, headerValue);
        return headers;
    }
}
