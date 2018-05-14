package com.mytest.teligen.controller;

import static com.mytest.teligen.controller.TemperatureController.TEMPERATURE_PATH;
import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.junit.Assert.*;

import com.mytest.teligen.controller.config.TemperatureControllerTestConfig;
import com.mytest.teligen.entity.CityTemperature;
import com.mytest.teligen.service.TemperatureServiceImpl;
import org.junit.Before;
import org.junit.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by vita on 13.05.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TemperatureController.class, TemperatureControllerTestConfig.class})
@AutoConfigureMockMvc(secure = false)
@EnableWebMvc
@ActiveProfiles(profiles = "test")
public class TemperatureControllerTest  {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TemperatureServiceImpl service;

    @Before
    public void before(){
        reset(service);
    }
    
    @Test
    public void shouldReturnOkStatusAndCorrectJsonResponseTest() throws Exception {
        CityTemperature temperature = new CityTemperature();
        temperature.setMinTemperature(5);
        temperature.setTemperature(10);
        when(service.get("Moscow", "ru")).thenReturn(temperature);
        String temperatureEndPoint = TEMPERATURE_PATH + "/?city=Moscow&country=ru";
        mvc.perform(get(temperatureEndPoint))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.minimumExpectedTemperature").value("5"))
                .andExpect(jsonPath("$.temperature").value("10"));
    }
    
    @Test
    public void shouldReturnServerErrorStatusAndEmptyResponseTest() throws Exception{
        when(service.get(any(String.class), any(String.class))).thenThrow(Exception.class);
        String temperatureEndPoint = TEMPERATURE_PATH + "/?city=Moscow&country=ru";
        mvc.perform(get(temperatureEndPoint))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(""));
    }

}
