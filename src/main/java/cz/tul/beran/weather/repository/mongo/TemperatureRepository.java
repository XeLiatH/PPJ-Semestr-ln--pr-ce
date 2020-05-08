package cz.tul.beran.weather.repository.mongo;

import cz.tul.beran.weather.entity.mongo.Temperature;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TemperatureRepository extends MongoRepository<Temperature, Long> {
}
