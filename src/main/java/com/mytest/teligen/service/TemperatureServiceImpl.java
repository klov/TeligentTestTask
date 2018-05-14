package com.mytest.teligen.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytest.teligen.entity.CityTemperature;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.net.URLEncoder;
import org.springframework.stereotype.Service;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.ClientErrorException;
import org.springframework.core.env.Environment;

/**
 * Created by vita on 12.05.2018.
 */
@Service
public class TemperatureServiceImpl {
    
    
    @Autowired
    Environment enviroment;
    
 
    public CityTemperature get(String city, String country) throws Exception {
        HttpClient httpClient = new HttpClient();
        httpClient.start();
           String url = "http://api.worldweatheronline.com/premium/v1/weather.ashx?key=9b7ed6bf5c3d4350871121003181205&"
                +"q="+URLEncoder.encode(city, "utf-8")+","
                +URLEncoder.encode(country,"utf-8")+"&num_of_days=3" 
                +"&tp=24&format=json";
        ContentResponse response  = httpClient.GET(url);
        if(response.getStatus()>=300){
           throw new ClientErrorException(response.getStatus()) ;
        }
        String jsonAnswer = response.getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
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
        httpClient.stop();
        return cityTemperature;
    }

    public Environment getEnviroment() {
        return enviroment;
    }

    public void setEnviroment(Environment enviroment) {
        this.enviroment = enviroment;
    }
    
}
