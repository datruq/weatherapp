package com.finleap.casestudy.weatherapp.web.app;

import com.finleap.casestudy.weatherapp.domain.entity.ForcastWeatherMetricsEntity;
import com.finleap.casestudy.weatherapp.domain.usecase.WeatherAppUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("weather/")
@Api(description = "Set of endpoints for consult on WeatherApp.")
public class WeatherAppController {

    @Autowired
    WeatherAppUseCase weatherAppUseCase;

    @GetMapping(path = "data/{city}")
    @ApiOperation("Returns an average weather of an specific city.")
    public ForcastWeatherMetricsEntity getWatherReportByCity(@PathVariable("city") String city){
        List<ForcastWeatherMetricsEntity> forcastWeatherMetrics = new ArrayList<>();
        return weatherAppUseCase.getWeatjerReportForSpecificCity(city);
    }

    @GetMapping("data")
    @ApiOperation("Returns an average weather of Medellín.")
    public ForcastWeatherMetricsEntity getWatherReport(){
        List<ForcastWeatherMetricsEntity> forcastWeatherMetrics = new ArrayList<>();
        return weatherAppUseCase.getWeatjerReportForSpecificCity("medellín");
    }

}
