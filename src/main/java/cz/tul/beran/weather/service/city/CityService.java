package cz.tul.beran.weather.service.city;

import cz.tul.beran.weather.repository.mysql.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CityService {

    @Autowired
    private CityRepository cityRepository;
}
