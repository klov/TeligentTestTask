package com.mytest.teligen.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytest.teligen.entity.CityTemperature;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.net.URLEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by vita on 12.05.2018.
 */
@Service
public class TemperatureServiceImpl {
    
    
    @Autowired
    Environment enviroment;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private ObjectMapper mapper;
    
 
    public CityTemperature get(String city, String country) throws Exception {
        String key = enviroment.getProperty("worldweather.key");
        if(key==null||key.isEmpty()){
            key = "9b7ed6bf5c3d4350871121003181205";
        }
        String url = "http://api.worldweatheronline.com/premium/v1/weather.ashx?key="
                + key + "&q="+URLEncoder.encode(city, "utf-8")+","+
                URLEncoder.encode(country,"utf-8")+"&num_of_days=3"+ 
                "&tp=24&format=json";
        String jsonAnswer  = restTemplate.getForObject(url, String.class);
        CityTemperature cityTemperature  =  new CityTemperature();
        JsonNode  root = mapper.readTree(jsonAnswer);
        JsonNode dataNode = root.get("data");
        JsonNode conditionNode =  dataNode.get("current_condition").get(0);
        cityTemperature.setTemperature(conditionNode.get("temp_C").asInt());
        JsonNode weathers = dataNode.get("weather");
        int minimalTemp = 999;
        for (JsonNode node : weathers){
            int temp = node.get("mintempC").asInt();
            minimalTemp = (temp<minimalTemp)?temp:minimalTemp;
        }
        cityTemperature.setMinTemperature(minimalTemp);
        return cityTemperature;
    }

    public Environment getEnviroment() {
        return enviroment;
    }

    public void setEnviroment(Environment enviroment) {
        this.enviroment = enviroment;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }
    
}
