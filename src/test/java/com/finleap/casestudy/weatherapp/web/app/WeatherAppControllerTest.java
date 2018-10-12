package com.finleap.casestudy.weatherapp.web.app;

import com.finleap.casestudy.weatherapp.domain.usecase.WeatherAppUseCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
    public void shouldReturnTheTestNoArgs() throws Exception {

        doReturn("").when(weatherAppUseCase).getWeatjerReportForSpecificCity("");

        this.mockMvc.perform(get("/weather/data"))
                .andExpect(status().isOk())
                .andExpect(content().string("01/01/1890"));
        verify(weatherAppUseCase, times(1)).getWeatjerReportForSpecificCity("");
        verifyNoMoreInteractions(weatherAppUseCase);

    }

    @Test
    public void shouldReturnTheTest() throws Exception {

        doReturn("").when(weatherAppUseCase).getWeatjerReportForSpecificCity("");

        this.mockMvc.perform(get("/weather/data/{city}", "medellin"))
                .andExpect(status().isOk())
                .andExpect(content().string("01/01/1890"));
        verify(weatherAppUseCase, times(1)).getWeatjerReportForSpecificCity("");
        verifyNoMoreInteractions(weatherAppUseCase);

    }

}
