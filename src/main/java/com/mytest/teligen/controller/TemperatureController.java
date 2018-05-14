package com.mytest.teligen.controller;

import static com.mytest.teligen.controller.TemperatureController.TEMPERATURE_PATH;
import com.mytest.teligen.entity.CityTemperature;
import com.mytest.teligen.service.TemperatureServiceImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(path = TEMPERATURE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class TemperatureController {

    public static final String TEMPERATURE_PATH ="/temperature";


    private TemperatureServiceImpl temperatureService;

    @RequestMapping(method = RequestMethod.GET)
    public CityTemperature getTemperature(@RequestParam("city") String city, @RequestParam("country") String country ) throws Exception {     
      CityTemperature temperature = temperatureService.get(city,country);
      return temperature;
    }
    
   @ExceptionHandler(Exception.class)
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ModelAndView handleError(HttpServletRequest req, Exception ex) {
    Logger.getLogger(TemperatureController.class.getName()).log(Level.SEVERE, null, ex);
    ModelAndView mav = new ModelAndView();
    return mav;
  }

    public TemperatureServiceImpl getTemperatureService() {
        return temperatureService;
    }

    @Autowired
    public void setTemperatureService(TemperatureServiceImpl temperatureService) {
        this.temperatureService = temperatureService;
    }


}

