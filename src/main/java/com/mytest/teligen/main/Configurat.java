package com.mytest.teligen.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Configurat {

    public Configurat() {
    }
    
    @Bean
    public ObjectMapper objectMapper(){
    return new ObjectMapper();
            }
    
   
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
