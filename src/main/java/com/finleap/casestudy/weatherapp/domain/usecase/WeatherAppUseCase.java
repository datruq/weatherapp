package com.finleap.casestudy.weatherapp.domain.usecase;

import com.finleap.casestudy.weatherapp.utils.DateUtils;
import com.finleap.casestudy.weatherapp.api.dto.OpenWeatherMapDTO;
import com.finleap.casestudy.weatherapp.domain.entity.ForcastWeatherMetricsEntity;
import com.finleap.casestudy.weatherapp.domain.gateway.WeatherAppService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherAppUseCase {

    private WeatherAppService weatherAppService;

    public WeatherAppUseCase(WeatherAppService weatherAppService) {
        this.weatherAppService = weatherAppService;
    }

    public ForcastWeatherMetricsEntity getWeatjerReportForSpecificCity(String city) {
        try {
            return openWeatherMapDTOToForcastWeatherMetrics(
                    weatherAppService.getReportFromCityForTheLastThreeHours(city));
        } catch (IOException e) {
            return null;
        }

    }

    public ForcastWeatherMetricsEntity openWeatherMapDTOToForcastWeatherMetrics(OpenWeatherMapDTO openWeatherMapDTO){
        ForcastWeatherMetricsEntity forcastWeatherMetrics = new ForcastWeatherMetricsEntity();
        forcastWeatherMetrics.setCityName(openWeatherMapDTO.getForcastWeatherCity().getCity());
        forcastWeatherMetrics.setCode(openWeatherMapDTO.getCode());
        forcastWeatherMetrics.setMessage(openWeatherMapDTO.getMessage());
        forcastWeatherMetrics.setDailyAverage(followingThreeDaysFilter(openWeatherMapDTO.getForcastWeathers()));
        forcastWeatherMetrics.setNightlyAverage(followingThreeDaysFilter(openWeatherMapDTO.getForcastWeathers()));
        forcastWeatherMetrics.setPressureAverage(followingThreeDaysFilter(openWeatherMapDTO.getForcastWeathers()));
        return forcastWeatherMetrics;
    }

    public List<OpenWeatherMapDTO.ForcastWeather> followingThreeDaysFilter(
            List<OpenWeatherMapDTO.ForcastWeather> forcastWeatherList){
        return forcastWeatherList.stream().filter( threeDays ->
                DateUtils.stringDateTimeToLocalDateTime(threeDays.getForcastWeatherDateTime())
                        .isBefore(LocalDateTime.now().plusDays(3)))
                .collect(Collectors.toList());
    }
}
