package com.finleap.casestudy.weatherapp.domain.entity;

import com.finleap.casestudy.weatherapp.Utils.DateUtils;
import com.finleap.casestudy.weatherapp.api.dto.OpenWeatherMapDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ForcastWeatherMetricsEntity {

    private static final LocalTime SIXTEEN_TIME = LocalTime.of(16, 0);
    private static final LocalTime SIX_TIME = LocalTime.of(6, 0);

    @Setter
    int code;
    @Setter
    String message;
    @Setter
    String cityName;
    double dailyAverage;
    double nightlyAverage;
    double pressureAverage;

    public double setDailyAverage(List<OpenWeatherMapDTO.ForcastWeather> forcastWeatherList){
        return this.dailyAverage = getDailyTime(forcastWeatherList).stream().mapToDouble(daily ->
                daily.getForcastWeatherMain().getTemperature()).average().orElse(Double.NaN);
    }

    public double setNightlyAverage(List<OpenWeatherMapDTO.ForcastWeather> forcastWeatherList){
        return this.nightlyAverage = getNightlyTime(forcastWeatherList).stream().mapToDouble(nightly ->
                nightly.getForcastWeatherMain().getTemperature()).average().orElse(Double.NaN);
    }

    public double setPressureAverage(List<OpenWeatherMapDTO.ForcastWeather> forcastWeatherList){
        return this.pressureAverage = forcastWeatherList.stream().mapToDouble(pressure ->
                pressure.getForcastWeatherMain().getPressure()).average().orElse(Double.NaN);
    }

    private List<OpenWeatherMapDTO.ForcastWeather> getDailyTime(
            List<OpenWeatherMapDTO.ForcastWeather> forcastWeatherList){
        return forcastWeatherList.stream().filter( threeDays ->{
            LocalDateTime dateTime = DateUtils.stringDateTimeToLocalDateTime(threeDays.getForcastWeatherDateTime());
            return isBetweenSixAndSixteen(dateTime);
                }).collect(Collectors.toList());
    }

    private List<OpenWeatherMapDTO.ForcastWeather> getNightlyTime(
            List<OpenWeatherMapDTO.ForcastWeather> forcastWeatherList){
        return forcastWeatherList.stream().filter( threeDays ->{
            LocalDateTime dateTime = DateUtils.stringDateTimeToLocalDateTime(threeDays.getForcastWeatherDateTime());
            return !isBetweenSixAndSixteen(dateTime);
        }).collect(Collectors.toList());
    }

    private boolean isBetweenSixAndSixteen(LocalDateTime actualDate){
        return (actualDate.isBefore(LocalDateTime.of(actualDate.toLocalDate(), SIXTEEN_TIME)) &&
                actualDate.isAfter(LocalDateTime.of(actualDate.toLocalDate(), SIX_TIME)));
    }

}
