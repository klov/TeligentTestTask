package com.mytest.teligen.controller.config;

import testTask.teligen.service.TemperatureServiceImpl;
import static org.mockito.Mockito.mock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vita on 13.05.2018.
 */
@Configuration
public class TemperatureControllerTestConfig {
    
    @Bean
    public TemperatureServiceImpl temperatureServiceMock(){
        return mock(TemperatureServiceImpl.class);
    }
}
