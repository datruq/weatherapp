package com.finleap.casestudy.weatherapp.domain.usecase;

import com.finleap.casestudy.weatherapp.api.dto.OpenWeatherMapDTO;
import com.finleap.casestudy.weatherapp.domain.entity.ForcastWeatherMetricsEntity;
import com.finleap.casestudy.weatherapp.domain.gateway.WeatherAppService;
import com.finleap.casestudy.weatherapp.utils.DateUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class WeatherAppUseCaseTest {

    private static final int THE_FOLLOWING_THREE_DAYS = 4;
    private static final String BERLIN = "berlin";
    private static final String HTTP_STATUS_OK = "Status OK";
    private static final int HTTP_STATUS_CODE_OK = 200;
    private static final int YEAR = LocalDate.now().getYear();
    private static final int MONTH = LocalDate.now().getMonth().getValue();
    private static final int HOUR = 14;
    private static final int MINUTE = 00;
    private static final double TEMPERATURE_START = 0D;
    private static final double TEMPERATURE_END = 50D;
    private static final double PRESSURE_START = 0D;
    private static final double PRESSURE_END = 1500D;
    private WeatherAppService weatherAppService;
    private WeatherAppUseCase weatherAppUseCase;

    @Before
    public void setup(){
        weatherAppService = Mockito.mock(WeatherAppService.class);
        weatherAppUseCase = new WeatherAppUseCase(weatherAppService);
    }

    @Test
    public void getWeatjerReportForSpecificCityMethodTest() throws IOException {
        when(weatherAppService.getReportFromCityForTheLastThreeHours(Mockito.anyString()))
                .thenReturn(setOpenWeatherMapDTO());
        ForcastWeatherMetricsEntity getWeatherReportResponse =
                weatherAppUseCase.getWeatjerReportForSpecificCity(BERLIN);
        Assertions.assertThat(getWeatherReportResponse.getCityName()).isEqualTo(BERLIN);
        Assertions.assertThat(getWeatherReportResponse.getMessage()).isEqualTo(HTTP_STATUS_OK);
        Assertions.assertThat(getWeatherReportResponse.getCode()).isEqualTo(HTTP_STATUS_CODE_OK);
        Assertions.assertThat(getWeatherReportResponse.getDailyAverage()).isBetween(TEMPERATURE_START, TEMPERATURE_END);
        Assertions.assertThat(getWeatherReportResponse.getNightlyAverage()).isBetween(TEMPERATURE_START,TEMPERATURE_END);
        Assertions.assertThat(getWeatherReportResponse.getPressureAverage()).isBetween(PRESSURE_START, PRESSURE_END);
    }

    @Test
    public void followingThreeDaysFilterMethodTest(){
        List<OpenWeatherMapDTO.ForcastWeather> threeDaysFilterResponse =
                weatherAppUseCase.followingThreeDaysFilter(setListOfForcastWeather());
        Assertions.assertThat(threeDaysFilterResponse.size()).isLessThan(THE_FOLLOWING_THREE_DAYS);
    }

    @Test
    public void openWeatherMapDTOToForcastWeatherMetricsMethodTest(){
        ForcastWeatherMetricsEntity transformMethodResponse =
                weatherAppUseCase.openWeatherMapDTOToForcastWeatherMetrics(setOpenWeatherMapDTO());
        Assertions.assertThat(transformMethodResponse.getCityName()).isEqualTo(BERLIN);
        Assertions.assertThat(transformMethodResponse.getMessage()).isEqualTo(HTTP_STATUS_OK);
        Assertions.assertThat(transformMethodResponse.getCode()).isEqualTo(HTTP_STATUS_CODE_OK);
        Assertions.assertThat(transformMethodResponse.getDailyAverage()).isBetween(TEMPERATURE_START, TEMPERATURE_END);
        Assertions.assertThat(transformMethodResponse.getNightlyAverage()).isBetween(TEMPERATURE_START,TEMPERATURE_END);
        Assertions.assertThat(transformMethodResponse.getPressureAverage()).isBetween(PRESSURE_START, PRESSURE_END);
    }

    private OpenWeatherMapDTO setOpenWeatherMapDTO(){
        OpenWeatherMapDTO openWeatherMapDTO = new OpenWeatherMapDTO();
        OpenWeatherMapDTO.ForcastWeatherCity forcastWeatherCity = new OpenWeatherMapDTO.ForcastWeatherCity();
        openWeatherMapDTO.setMessage(HTTP_STATUS_OK);
        openWeatherMapDTO.setCode(HTTP_STATUS_CODE_OK);
        openWeatherMapDTO.setForcastWeathers(setListOfForcastWeather());
        forcastWeatherCity.setCity(BERLIN);
        openWeatherMapDTO.setForcastWeatherCity(forcastWeatherCity);
        return openWeatherMapDTO;
    }

    private List<OpenWeatherMapDTO.ForcastWeather> setListOfForcastWeather(){
        List<OpenWeatherMapDTO.ForcastWeather> forcastWeatherList = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            OpenWeatherMapDTO.ForcastWeather forcastWeather = new OpenWeatherMapDTO.ForcastWeather();
            OpenWeatherMapDTO.ForcastWeatherMain forcastWeatherMain = new OpenWeatherMapDTO.ForcastWeatherMain();
            forcastWeather.setForcastWeatherDateTime(
                    DateUtils.localDateTimeToStringDateTime(LocalDateTime.of(YEAR, MONTH,
                            LocalDateTime.now().getDayOfMonth() + i, HOUR +i, MINUTE)));
            forcastWeatherMain.setTemperature(i);
            forcastWeatherMain.setPressure(i*i);
            forcastWeather.setForcastWeatherMain(forcastWeatherMain);
            forcastWeatherList.add(forcastWeather);
        }
        return forcastWeatherList;
    }
}
