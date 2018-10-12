package com.finleap.casestudy.weatherapp.config;

import com.finleap.casestudy.weatherapp.api.OpenWeatherMapApiImpl;
import com.finleap.casestudy.weatherapp.domain.gateway.WeatherAppService;
import com.finleap.casestudy.weatherapp.domain.usecase.WeatherAppUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainBeans {

    @Bean
    public WeatherAppService weatherAppService(){
        return new OpenWeatherMapApiImpl();
    }

    @Bean
    public WeatherAppUseCase weatherAppUseCase(WeatherAppService weatherAppService){
        return new WeatherAppUseCase(weatherAppService);
    }
}
