package com.mytest.teligen.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Created by vita on 13.05.2018.
 */
public class CityTemperature implements Serializable {
    

    public CityTemperature() {
    }
    
    @JsonProperty("temperature")
    private int temperature;
    
    @JsonProperty("minimumExpectedTemperature")
    private int minTemperature;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(int minTemperature) {
        this.minTemperature = minTemperature;
    }
    
}
