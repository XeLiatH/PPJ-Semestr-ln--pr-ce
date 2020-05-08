package cz.tul.beran.weather.service.temperature;

import cz.tul.beran.weather.repository.mongo.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TemperatureService {

    @Autowired
    private TemperatureRepository temperatureRepository;
}
