package cz.tul.beran.weather.repository;

import cz.tul.beran.weather.entity.Temperature;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TemperatureRepository extends MongoRepository<Temperature, String> {
}
