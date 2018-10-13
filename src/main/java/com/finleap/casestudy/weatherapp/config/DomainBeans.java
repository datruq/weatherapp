package com.finleap.casestudy.weatherapp.config;

import com.finleap.casestudy.weatherapp.api.OpenWeatherMapApiImpl;
import com.finleap.casestudy.weatherapp.domain.gateway.WeatherAppService;
import com.finleap.casestudy.weatherapp.domain.usecase.WeatherAppUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DomainBeans {

    @Value("${x.api.key}")
    private String appKeyName;
    @Value("${open.weather.map.api.key}")
    private String appKeyValue;
    @Value("${open.weather.map.url}")
    private String openWeatherMapUrl;

    @Bean
    public WeatherAppService weatherAppService(){
        return new OpenWeatherMapApiImpl(appKeyName, appKeyValue, openWeatherMapUrl, new RestTemplate());
    }

    @Bean
    public WeatherAppUseCase weatherAppUseCase(WeatherAppService weatherAppService){
        return new WeatherAppUseCase(weatherAppService);
    }
}
