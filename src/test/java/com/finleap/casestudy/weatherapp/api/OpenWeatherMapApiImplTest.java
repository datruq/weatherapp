package com.finleap.casestudy.weatherapp.api;

import com.finleap.casestudy.weatherapp.api.dto.OpenWeatherMapDTO;
import com.finleap.casestudy.weatherapp.utils.FileUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

public class OpenWeatherMapApiImplTest {

    private static final String APP_KEY_NAME = "x-api-key";
    private static final String APP_KEY_VALUE = "1234";
    private static final String APP_KEY_VALUE_RESPONSE = "[1234]";
    private static final String HTTP_ENTITY_PARAMETERS = "parameters";
    private static final String OPEN_WEATHER_MAP_API_JSON = "json/open-weather-map-api.json";
    private static final String BERLIN = "berlin";
    public static final int HTTP_STATUS_CODE = 200;
    public static final int HTTP_STATUS_CODE_OK = 200;

    OpenWeatherMapApiImpl openWeatherMapApi;

    @Before
    public void setup() {
        openWeatherMapApi = new OpenWeatherMapApiImpl();
    }

    @Test
    public void jsonStringToOpenWeatherMapDTOMethodTest() throws IOException {
        OpenWeatherMapDTO responseMapper = openWeatherMapApi.jsonStringToOpenWeatherMapDTO(
                FileUtils.readResource(OPEN_WEATHER_MAP_API_JSON));
        Assertions.assertThat(responseMapper.getCode()).isEqualTo(HTTP_STATUS_CODE_OK);
    }

    @Test
    public void addHttpHeadersMethodTest() {
        HttpHeaders addHttpHeadersResponse = openWeatherMapApi.addHttpHeader(APP_KEY_NAME, APP_KEY_VALUE);
        Assertions.assertThat(addHttpHeadersResponse.get(APP_KEY_NAME).toString()).isEqualTo(APP_KEY_VALUE_RESPONSE);
    }

    @Test
    public void setHttpHeadersMethodTest() {
        HttpEntity<String> setHttpHeadersResponse = openWeatherMapApi.setHttpHeaders(
                addHttpHeader(APP_KEY_NAME, APP_KEY_VALUE));
        Assertions.assertThat(setHttpHeadersResponse.getHeaders().get(APP_KEY_NAME).toString()).isEqualTo(APP_KEY_VALUE_RESPONSE);
    }

    private OpenWeatherMapDTO setOpenWeatherMapDTOFromMock(){
        OpenWeatherMapDTO openWeatherMapDTO = new OpenWeatherMapDTO();
        openWeatherMapDTO.setCode(HTTP_STATUS_CODE);
        return openWeatherMapDTO;
    }

    private HttpEntity<String> setHttpHeadersMock(){
        return new HttpEntity<>(HTTP_ENTITY_PARAMETERS, addHttpHeader(APP_KEY_NAME, APP_KEY_VALUE));
    }

    private HttpHeaders addHttpHeader(String headerKey, String headerValue) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(headerKey, headerValue);
        return headers;
    }
}
