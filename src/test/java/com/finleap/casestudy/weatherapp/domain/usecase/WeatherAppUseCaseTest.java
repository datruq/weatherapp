package com.finleap.casestudy.weatherapp.domain.usecase;


import com.finleap.casestudy.weatherapp.utils.DateUtils;
import com.finleap.casestudy.weatherapp.api.dto.OpenWeatherMapDTO;
import com.finleap.casestudy.weatherapp.domain.gateway.WeatherAppService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WeatherAppUseCaseTest {

    public static final int THE_FOLLOWING_THREE_DAYS = 4;
    private WeatherAppService weatherAppService;
    private WeatherAppUseCase weatherAppUseCase;

    @Before
    public void setup(){
        weatherAppService = Mockito.mock(WeatherAppService.class);
        weatherAppUseCase = new WeatherAppUseCase(weatherAppService);
    }

    @Test
    public void followingThreeDaysFilterTest(){
        List<OpenWeatherMapDTO.ForcastWeather> responseService =
                weatherAppUseCase.followingThreeDaysFilter(setListOfForcastWeather());
        Assertions.assertThat(responseService.size()).isLessThan(THE_FOLLOWING_THREE_DAYS);
    }

    private List<OpenWeatherMapDTO.ForcastWeather> setListOfForcastWeather(){
        List<OpenWeatherMapDTO.ForcastWeather> forcastWeatherList = new ArrayList<>();
        for (long i = 1; i < 7; i++) {
            OpenWeatherMapDTO.ForcastWeather forcastWeather = new OpenWeatherMapDTO.ForcastWeather();
            forcastWeather.setForcastWeatherDateTime(
                    DateUtils.localDateTimeToStringDateTime(LocalDateTime.now().plusDays(i)));
            forcastWeatherList.add(forcastWeather);
        }
        return forcastWeatherList;
    }
}
