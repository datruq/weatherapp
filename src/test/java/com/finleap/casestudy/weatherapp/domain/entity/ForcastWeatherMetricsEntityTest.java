package com.finleap.casestudy.weatherapp.domain.entity;

import com.finleap.casestudy.weatherapp.utils.DateUtils;
import com.finleap.casestudy.weatherapp.api.dto.OpenWeatherMapDTO;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ForcastWeatherMetricsEntityTest {

    ForcastWeatherMetricsEntity followingThreeDaysFilter;

    @Before
    public void setup(){
        followingThreeDaysFilter = new ForcastWeatherMetricsEntity();
    }

    @Test
    public void setPressureAverageTest(){
        double responseService = followingThreeDaysFilter.setPressureAverage(setListOfForcastWeatherForPressure());
        Assertions.assertThat(responseService).isBetween(100D, 1500D);
    }

    @Test
    public void setDailyAverageTest(){
        double responseService = followingThreeDaysFilter.setDailyAverage(setListOfForcastWeatherForTemperature());
        Assertions.assertThat(responseService).isBetween(0D, 50D);
    }

    @Test
    public void setNightlyAverageTest(){
        double responseService = followingThreeDaysFilter.setNightlyAverage(setListOfForcastWeatherForTemperature());
        Assertions.assertThat(responseService).isBetween(0D, 50D);
    }

    private List<OpenWeatherMapDTO.ForcastWeather> setListOfForcastWeatherForPressure(){
        List<OpenWeatherMapDTO.ForcastWeather> forcastWeatherList = new ArrayList<>();
        for (long i = 1; i < 5; i++) {
            OpenWeatherMapDTO.ForcastWeather forcastWeather = new OpenWeatherMapDTO.ForcastWeather();
            OpenWeatherMapDTO.ForcastWeatherMain forcastWeatherMain = new OpenWeatherMapDTO.ForcastWeatherMain();
            forcastWeatherMain.setPressure((int)(Math.random() * ((1500 - 100) + 1)) + 100);
            forcastWeather.setForcastWeatherMain(forcastWeatherMain);
            forcastWeatherList.add(forcastWeather);
        }
        return forcastWeatherList;
    }

    private List<OpenWeatherMapDTO.ForcastWeather> setListOfForcastWeatherForTemperature(){
        List<OpenWeatherMapDTO.ForcastWeather> forcastWeatherList = new ArrayList<>();
        for (long i = 0; i < 6; i++) {
            OpenWeatherMapDTO.ForcastWeather forcastWeather = new OpenWeatherMapDTO.ForcastWeather();
            OpenWeatherMapDTO.ForcastWeatherMain forcastWeatherMain = new OpenWeatherMapDTO.ForcastWeatherMain();
            forcastWeatherMain.setTemperature((int)(Math.random() * ((50 - 0) + 1)) + 0);
            forcastWeather.setForcastWeatherDateTime(DateUtils.localDateTimeToStringDateTime(
                    LocalDateTime.now().plusDays((int)i/2).plusHours(i*i)));
            forcastWeather.setForcastWeatherMain(forcastWeatherMain);
            forcastWeatherList.add(forcastWeather);
        }
        return forcastWeatherList;
    }
}
