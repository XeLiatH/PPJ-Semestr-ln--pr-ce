package cz.tul.beran.weather.controller.rest;

import cz.tul.beran.weather.service.city.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

    @Autowired
    private CityService cityService;
}
