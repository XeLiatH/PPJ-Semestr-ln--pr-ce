package cz.tul.beran.weather.service.country;

import cz.tul.beran.weather.repository.mysql.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CountryService {

    @Autowired
    private CountryRepository countryRepository;
}
