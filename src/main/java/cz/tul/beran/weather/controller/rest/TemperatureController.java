package cz.tul.beran.weather.controller.rest;

import cz.tul.beran.weather.service.temperature.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemperatureController {

    @Autowired
    private TemperatureService temperatureService;
}
