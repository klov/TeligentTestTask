package testTask.teligen.controller;

import org.springframework.web.bind.annotation.*;
import testTask.teligen.service.RecordDublicateException;
import testTask.teligen.service.RecordNotFoundException;
import testTask.teligen.service.TemperatureServiceImpl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import testTask.teligen.entity.CityTemperatureDto;

import static testTask.teligen.controller.TemperatureController.TEMPERATURE_PATH;

@RestController
@RequestMapping(path = TEMPERATURE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class TemperatureController {

    public static final String TEMPERATURE_PATH = "/temperature";

    @Autowired
    private TemperatureServiceImpl temperatureService;

    @GetMapping(path = "/all")
    public List<CityTemperatureDto> getAllTemperature(){
        return temperatureService.getAll();
    }

    @GetMapping
    public CityTemperatureDto getTemperature(@RequestParam("city") String city, @RequestParam("day") long date ) throws Exception {
        SimpleDateFormat objSDF = new SimpleDateFormat();
        CityTemperatureDto temperature = temperatureService.get(city, new Date(date));
        return temperature;
    }

    @PostMapping
    public void saveTemperature(@Valid @RequestBody CityTemperatureDto record) throws RecordDublicateException {
        temperatureService.save(record);
    }

    @DeleteMapping
    public void deleteTemperature(@RequestParam("city") String city, @RequestParam("day") long date ){
        temperatureService.delete(city,new Date(date));
    }

    @PutMapping
    public void updateTemperature(@Valid @RequestBody CityTemperatureDto record) throws RecordNotFoundException {
        temperatureService.update(record);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, HttpServletResponse res, Exception ex) throws IOException {
        Logger.getLogger(TemperatureController.class.getName()).log(Level.SEVERE, null, ex);
        ModelAndView mav = new ModelAndView();
        res.setContentType("text/plain");
        ServletOutputStream outputStream = res.getOutputStream();
        outputStream.print(ex.getClass().getName());
        if(ex instanceof RecordNotFoundException){
            res.sendError(404);
        } else if(ex instanceof RecordDublicateException ){
            res.sendError(400);
        } else {
            res.sendError(500);
        }
        return mav;
    }

    public TemperatureServiceImpl getTemperatureService() {
        return temperatureService;
    }

    
    public void setTemperatureService(TemperatureServiceImpl temperatureService) {
        this.temperatureService = temperatureService;
    }

}
