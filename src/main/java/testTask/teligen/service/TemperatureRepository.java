package testTask.teligen.service;

import org.springframework.stereotype.Repository;
import testTask.teligen.entity.CityTemperatureDto;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TemperatureRepository {

    List<CityTemperatureDto> storage;

    public TemperatureRepository() {
        this.storage = new LinkedList<>();
        CityTemperatureDto dto = new CityTemperatureDto(0,34,new Date(),"Moscow");
        storage.add(dto);
    }

    public List<CityTemperatureDto> getAll() {
        return storage;
    }

    public CityTemperatureDto get(String city, Date day) throws RecordNotFoundException {
        return storage.stream().filter((e)->{
            return e.getCity().equals(city)&&e.getDay().equals(day);
        }).findFirst().orElseThrow(()-> new RecordNotFoundException());
    }

    public void save(CityTemperatureDto record) throws RecordDublicateException {
       if( storage.stream().anyMatch((e)->{
            return e.equals(record);
        })){
           throw new RecordDublicateException();
       }else{
           storage.add(record);
       }
    }


    public void update(CityTemperatureDto record) throws RecordNotFoundException {
        if(!storage.stream().anyMatch((e)->{
            return e.getDay().equals(record.getDay())
                    &&e.getCity().equals(record.getCity())
                    &&e.getVersion()==record.getVersion();
        })){
            throw new RecordNotFoundException();
        }
        storage.stream().forEach((e)->{
            if(e.getDay().equals(record.getDay())
                    &&e.getCity().equals(record.getCity())
                    &&e.getVersion()==record.getVersion()) {
                e.setTemperature(record.getTemperature());
            }
                });
    }

    public void detete(String city, Date date) {
        List<CityTemperatureDto> deleteRecord = storage.stream()
                .filter((e)->{return e.getCity().equals(city)&&e.getDay().equals(date);})
                .collect(Collectors.toList());
        storage.removeAll(deleteRecord);
    }
}
