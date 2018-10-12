package com.finleap.casestudy.weatherapp.domain.entity;

import com.finleap.casestudy.weatherapp.utils.DateUtils;
import com.finleap.casestudy.weatherapp.api.dto.OpenWeatherMapDTO;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
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
    public void setPressureAverageSetterTest(){
        double responseService = followingThreeDaysFilter.setPressureAverage(setListOfForcastWeatherForPressure());
        Assertions.assertThat(responseService).isBetween(100D, 1500D);
    }

    @Test
    public void setDailyAverageSetterTest(){
        double responseService = followingThreeDaysFilter.setDailyAverage(setListOfForcastWeatherForDailyTemperature());
        Assertions.assertThat(responseService).isBetween(0D, 50D);
    }

    @Test
    public void setNightlyAverageMethodTest(){
        double responseService = followingThreeDaysFilter.setNightlyAverage(setListOfForcastWeatherForNightlyTemperature());
        Assertions.assertThat(responseService).isBetween(0D, 50D);
    }

    @Test
    public void getDailyTimeMethodTest(){
        List<OpenWeatherMapDTO.ForcastWeather> responseService = followingThreeDaysFilter.getDailyTime(
                setListOfForcastWeatherForDailyTemperature());
        Assertions.assertThat(responseService.size()).isEqualTo(3);
    }

    @Test
    public void getNightlyTimeMethodTest(){
        List<OpenWeatherMapDTO.ForcastWeather> responseService = followingThreeDaysFilter.getNightlyTime(
                setListOfForcastWeatherForNightlyTemperature());
        Assertions.assertThat(responseService.size()).isEqualTo(1);
    }

    @Test
    public void isBetweenSixAndSixteenMethodTest(){
        boolean responseService = followingThreeDaysFilter.isBetweenSixAndSixteen(
                LocalDateTime.of(2018,10,12,10,00));
        Assertions.assertThat(responseService).isTrue();
    }

    @Test
    public void isNotBetweenSixAndSixteenMethodTest(){
        boolean responseService = followingThreeDaysFilter.isBetweenSixAndSixteen(
                LocalDateTime.of(2018,10,12,20,00));
        Assertions.assertThat(responseService).isFalse();
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

    private List<OpenWeatherMapDTO.ForcastWeather> setListOfForcastWeatherForDailyTemperature(){
        List<OpenWeatherMapDTO.ForcastWeather> forcastWeatherList = new ArrayList<>();
        OpenWeatherMapDTO.ForcastWeather forcastWeather = new OpenWeatherMapDTO.ForcastWeather();
        OpenWeatherMapDTO.ForcastWeatherMain forcastWeatherMain = new OpenWeatherMapDTO.ForcastWeatherMain();
        forcastWeatherMain.setTemperature(getRamdomTemperature());
        forcastWeather.setForcastWeatherDateTime(DateUtils.localDateTimeToStringDateTime(
                LocalDateTime.of(2018,10,12,6,0)));
        forcastWeather.setForcastWeatherMain(forcastWeatherMain);
        forcastWeatherList.add(forcastWeather);
        forcastWeather = new OpenWeatherMapDTO.ForcastWeather();
        forcastWeatherMain = new OpenWeatherMapDTO.ForcastWeatherMain();
        forcastWeatherMain.setTemperature(getRamdomTemperature());
        forcastWeather.setForcastWeatherDateTime(DateUtils.localDateTimeToStringDateTime(
                LocalDateTime.of(2018,10,12,9,22)));
        forcastWeather.setForcastWeatherMain(forcastWeatherMain);
        forcastWeatherList.add(forcastWeather);
        forcastWeather = new OpenWeatherMapDTO.ForcastWeather();
        forcastWeatherMain = new OpenWeatherMapDTO.ForcastWeatherMain();
        forcastWeatherMain.setTemperature(getRamdomTemperature());
        forcastWeather.setForcastWeatherDateTime(DateUtils.localDateTimeToStringDateTime(
                LocalDateTime.of(2018,10,12,13,11)));
        forcastWeather.setForcastWeatherMain(forcastWeatherMain);
        forcastWeatherList.add(forcastWeather);
        forcastWeather = new OpenWeatherMapDTO.ForcastWeather();
        forcastWeatherMain = new OpenWeatherMapDTO.ForcastWeatherMain();
        forcastWeatherMain.setTemperature(getRamdomTemperature());
        forcastWeather.setForcastWeatherDateTime(DateUtils.localDateTimeToStringDateTime(
                LocalDateTime.of(2018,10,12,17,12)));
        forcastWeather.setForcastWeatherMain(forcastWeatherMain);
        forcastWeatherList.add(forcastWeather);
        return forcastWeatherList;

    }

    private List<OpenWeatherMapDTO.ForcastWeather> setListOfForcastWeatherForNightlyTemperature(){
        List<OpenWeatherMapDTO.ForcastWeather> forcastWeatherList = new ArrayList<>();
        OpenWeatherMapDTO.ForcastWeather forcastWeather = new OpenWeatherMapDTO.ForcastWeather();
        OpenWeatherMapDTO.ForcastWeatherMain forcastWeatherMain = new OpenWeatherMapDTO.ForcastWeatherMain();
        forcastWeatherMain.setTemperature(getRamdomTemperature());
        forcastWeather.setForcastWeatherDateTime(DateUtils.localDateTimeToStringDateTime(
                LocalDateTime.of(2018,10,12,6,0)));
        forcastWeather.setForcastWeatherMain(forcastWeatherMain);
        forcastWeatherList.add(forcastWeather);
        forcastWeather = new OpenWeatherMapDTO.ForcastWeather();
        forcastWeatherMain = new OpenWeatherMapDTO.ForcastWeatherMain();
        forcastWeatherMain.setTemperature(getRamdomTemperature());
        forcastWeather.setForcastWeatherDateTime(DateUtils.localDateTimeToStringDateTime(
                LocalDateTime.of(2018,10,12,9,22)));
        forcastWeather.setForcastWeatherMain(forcastWeatherMain);
        forcastWeatherList.add(forcastWeather);
        forcastWeather = new OpenWeatherMapDTO.ForcastWeather();
        forcastWeatherMain = new OpenWeatherMapDTO.ForcastWeatherMain();
        forcastWeatherMain.setTemperature(getRamdomTemperature());
        forcastWeather.setForcastWeatherDateTime(DateUtils.localDateTimeToStringDateTime(
                LocalDateTime.of(2018,10,12,13,11)));
        forcastWeather.setForcastWeatherMain(forcastWeatherMain);
        forcastWeatherList.add(forcastWeather);
        forcastWeather = new OpenWeatherMapDTO.ForcastWeather();
        forcastWeatherMain = new OpenWeatherMapDTO.ForcastWeatherMain();
        forcastWeatherMain.setTemperature(getRamdomTemperature());
        forcastWeather.setForcastWeatherDateTime(DateUtils.localDateTimeToStringDateTime(
                LocalDateTime.of(2018,10,12,17,12)));
        forcastWeather.setForcastWeatherMain(forcastWeatherMain);
        forcastWeatherList.add(forcastWeather);
        return forcastWeatherList;
    }

    private int getRamdomTemperature(){
        return (int)(Math.random() * ((50 - 0) + 1)) + 0;
    }
}
