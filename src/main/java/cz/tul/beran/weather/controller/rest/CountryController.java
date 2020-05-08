package cz.tul.beran.weather.controller.rest;

import cz.tul.beran.weather.service.country.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;
}
