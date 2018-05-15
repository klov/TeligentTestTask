package com.mytest.teligen.controller.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytest.teligen.service.TemperatureServiceImpl;
import static org.mockito.Mockito.mock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by vita on 13.05.2018.
 */
@Configuration
public class TemperatureControllerTestConfig {
    
    @Bean
    public TemperatureServiceImpl temperatureServiceMock(){
        return mock(TemperatureServiceImpl.class);
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
        @Bean
    public ObjectMapper objectMapper(){
    return new ObjectMapper();
            }
}
