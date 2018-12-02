package com.mytest.teligen.controller;


import com.mytest.teligen.controller.config.TemperatureControllerTestConfig;
import org.junit.Before;

import org.junit.runner.RunWith;
import static org.mockito.Mockito.reset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import testTask.teligen.controller.TemperatureController;
import testTask.teligen.service.TemperatureServiceImpl;

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
    
//    @Test
//    public void shouldReturnOkStatusAndCorrectJsonResponseTest() throws Exception {
//        CityTemperatureDto temperature = new CityTemperatureDto();
//        temperature.setMinTemperature(5);
//        temperature.setTemperature(10);
//        when(service.get("Moscow", "ru")).thenReturn(temperature);
//        String temperatureEndPoint = TEMPERATURE_PATH + "/?city=Moscow&country=ru";
//        mvc.perform(get(temperatureEndPoint))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.minimumExpectedTemperature").value("5"))
//                .andExpect(jsonPath("$.temperature").value("10"));
//    }
//
//    @Test
//    public void shouldReturnServerErrorStatusAndEmptyResponseTest() throws Exception{
//        when(service.get(any(String.class), any(String.class))).thenThrow(Exception.class);
//        String temperatureEndPoint = TEMPERATURE_PATH + "/?city=Moscow&country=ru";
//        mvc.perform(get(temperatureEndPoint))
//                .andExpect(status().isInternalServerError())
//                .andExpect(content().string(""));
//    }

}
