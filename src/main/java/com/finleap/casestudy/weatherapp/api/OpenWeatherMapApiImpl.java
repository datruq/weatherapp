package com.finleap.casestudy.weatherapp.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finleap.casestudy.weatherapp.api.dto.OpenWeatherMapDTO;
import com.finleap.casestudy.weatherapp.domain.gateway.WeatherAppService;
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

    private static final String APP_KEY_NAME = "x-api-key";
    private static final String APP_KEY_VALUE = "0333f8d7cabd16ab8b1cc53077a59da1";
    private static final String OPEN_WEATHER_MAP_URL = "http://api.openweathermap.org/data/2.5/forecast?q={city}&units=metric";
    private static final String HTTP_ENTITY_PARAMETERS = "parameters";
    private static final String OPEN_WEATHER_MAP_CITY = "city";
    public static final int RAW_STATUS_CODE = 500;

    @Override
    public OpenWeatherMapDTO getReportFromCityForTheLastThreeHours(String city) throws IOException {
        return restApiCaller(city);
    }

    private OpenWeatherMapDTO restApiCaller(String city) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put(OPEN_WEATHER_MAP_CITY, city);
        HttpEntity<String> entity = setHttpHeaders(addHeader(APP_KEY_NAME, APP_KEY_VALUE));
        return jsonStringToOpenWeatherMapDTO(restTemplate.exchange(
                OPEN_WEATHER_MAP_URL, HttpMethod.GET, entity, String.class, params).getBody());
    }

    private OpenWeatherMapDTO jsonStringToOpenWeatherMapDTO(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, OpenWeatherMapDTO.class);
    }

    private HttpEntity<String> setHttpHeaders(HttpHeaders headers){
        return new HttpEntity<>(HTTP_ENTITY_PARAMETERS, headers);
    }

    private HttpHeaders addHeader(String headerKey, String headerValue){
        HttpHeaders headers = new HttpHeaders();
        headers.add(headerKey, headerValue);
        return headers;
    }
}
