package com.finleap.casestudy.weatherapp.web.app;

import com.finleap.casestudy.weatherapp.domain.entity.ForcastWeatherMetricsEntity;
import com.finleap.casestudy.weatherapp.domain.usecase.WeatherAppUseCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class WeatherAppControllerTest {

    public static final int HTTP_STATUS_OK = 200;
    public static final String HTTP_STATUS_MESSAGE_OK = "Status OK";
    public static final String LOCAL_CITY = "medellin";
    public static final String BERLIN = "berlin";
    private MockMvc mockMvc;

    @MockBean
    private WeatherAppUseCase weatherAppUseCase;

    @InjectMocks
    private WeatherAppController weatherAppController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(weatherAppController)
                .build();
    }

    @Test
    public void shouldReturnTheTestForBerlin() throws Exception {
        doReturn(setForcastWeatherMetricsEntity(BERLIN)).when(weatherAppUseCase)
                .getWeatjerReportForSpecificCity(BERLIN);
        this.mockMvc.perform(get("/weather/data/{city}", BERLIN))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"code\":200,\"message\":\"Status OK\",\"cityName\":" +
                        "\"berlin\",\"dailyAverage\":0.0,\"nightlyAverage\":0.0,\"pressureAverage\":0.0}"));
        verify(weatherAppUseCase, times(1)).getWeatjerReportForSpecificCity(BERLIN);
        verifyNoMoreInteractions(weatherAppUseCase);

    }

    @Test
    public void shouldReturnTheTestDefault() throws Exception {
        doReturn(setForcastWeatherMetricsEntity(LOCAL_CITY)).when(weatherAppUseCase)
                .getWeatjerReportForSpecificCity(Mockito.anyString());
        this.mockMvc.perform(get("/weather/data"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"code\":200,\"message\":\"Status OK\",\"cityName\":" +
                        "\"medellin\",\"dailyAverage\":0.0,\"nightlyAverage\":0.0,\"pressureAverage\":0.0}"));
        verify(weatherAppUseCase, times(1)).getWeatjerReportForSpecificCity(Mockito.anyString());
        verifyNoMoreInteractions(weatherAppUseCase);

    }

    private ForcastWeatherMetricsEntity setForcastWeatherMetricsEntity(String city){
        ForcastWeatherMetricsEntity forcastWeatherMetricsEntity = new ForcastWeatherMetricsEntity();
        forcastWeatherMetricsEntity.setCityName(city);
        forcastWeatherMetricsEntity.setCode(HTTP_STATUS_OK);
        forcastWeatherMetricsEntity.setMessage(HTTP_STATUS_MESSAGE_OK);
        return forcastWeatherMetricsEntity;
    }

}
