package testTask.teligen.entity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class CityTemperatureDto implements Serializable {
    

    public CityTemperatureDto() {
    }

    public CityTemperatureDto(int version, Integer temperature, Date day, String city) {
        this.version = version;
        this.temperature = temperature;
        this.day = day;
        this.city = city;
    }

    @NotNull
    private int version;

    @NotNull
    private Integer temperature;

    @NotNull
    private Date day;

    @NotNull
    private String city;

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityTemperatureDto that = (CityTemperatureDto) o;

        if (version != that.version) return false;
        if (!temperature.equals(that.temperature)) return false;
        if (!day.equals(that.day)) return false;
        return city.equals(that.city);
    }

    @Override
    public int hashCode() {
        int result = version;
        result = 31 * result + temperature.hashCode();
        result = 31 * result + day.hashCode();
        result = 31 * result + city.hashCode();
        return result;
    }
}
