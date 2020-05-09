package cz.tul.beran.weather.repository.mongo;

import cz.tul.beran.weather.entity.mongo.Temperature;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface TemperatureRepository extends MongoRepository<Temperature, Long> {

  List<Temperature> findByCountryCode(String countryCode);
  List<Temperature> findByCreatedAtBefore(Date createdAt);
}
