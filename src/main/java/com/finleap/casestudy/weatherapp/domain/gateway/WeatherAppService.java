package com.finleap.casestudy.weatherapp.domain.gateway;

import com.finleap.casestudy.weatherapp.api.dto.OpenWeatherMapDTO;

import java.io.IOException;

public interface WeatherAppService {
    OpenWeatherMapDTO getReportFromCityForTheLastThreeHours(String city) throws IOException;
}
