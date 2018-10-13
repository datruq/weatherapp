package com.finleap.casestudy.weatherapp.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finleap.casestudy.weatherapp.api.dto.OpenWeatherMapDTO;
import com.finleap.casestudy.weatherapp.domain.gateway.WeatherAppService;
import com.finleap.casestudy.weatherapp.exceptions.WeatherAppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class OpenWeatherMapApiImpl implements WeatherAppService {

    private static final String HTTP_ENTITY_PARAMETERS = "parameters";
    private static final String OPEN_WEATHER_MAP_CITY = "city";

    private String appKeyName;
    private String appKeyValue;
    private String openWeatherMapUrl;
    private RestTemplate restTemplate;

    @Autowired
    public OpenWeatherMapApiImpl(String appKeyName, String appKeyValue, String openWeatherMapUrl, RestTemplate restTemplate){
        this.appKeyName = appKeyName;
        this.appKeyValue = appKeyValue;
        this.openWeatherMapUrl = openWeatherMapUrl;
        this.restTemplate = restTemplate;
    }


    @Override
    public OpenWeatherMapDTO getReportFromCityForTheLastThreeHours(String city) {
        Map<String, String> params = new HashMap<>();
        params.put(OPEN_WEATHER_MAP_CITY, city);
        HttpEntity<String> entity = setHttpHeaders(addHttpHeader(appKeyName, appKeyValue));
        OpenWeatherMapDTO openWeatherMapDTO;
        try {
            openWeatherMapDTO = jsonStringToOpenWeatherMapDTO(restTemplate.exchange(
                    openWeatherMapUrl, HttpMethod.GET, entity, String.class, params).getBody());
        }catch (HttpClientErrorException e){
            throw new WeatherAppException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (IOException e){
            throw new WeatherAppException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return openWeatherMapDTO;
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
