package testTask.teligen.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import testTask.teligen.entity.CityTemperatureDto;

@Service
public class TemperatureServiceImpl {

    private final TemperatureRepository repository;

    public TemperatureServiceImpl(TemperatureRepository repository) {
        this.repository = repository;
    }

    public CityTemperatureDto get(String city, Date day) throws RecordNotFoundException {
       return repository.get(city,day);
    }

    public void save(CityTemperatureDto record) throws RecordDublicateException {
        repository.save(record);

    }

    public List<CityTemperatureDto> getAll() {
       return  repository.getAll();
    }

    public void delete(String city, Date date) {
        repository.detete(city,date);
    }

    public void update(CityTemperatureDto record) throws RecordNotFoundException {
        repository.update(record);
    }
}
