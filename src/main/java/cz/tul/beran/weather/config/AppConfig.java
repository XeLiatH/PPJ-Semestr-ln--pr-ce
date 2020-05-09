package cz.tul.beran.weather.config;

import cz.tul.beran.weather.App;
import cz.tul.beran.weather.repository.mongo.SequenceRepository;
import cz.tul.beran.weather.repository.mongo.TemperatureRepository;
import cz.tul.beran.weather.repository.mysql.CityRepository;
import cz.tul.beran.weather.repository.mysql.CountryRepository;
import cz.tul.beran.weather.service.CsvWeather;
import cz.tul.beran.weather.service.SequenceService;
import cz.tul.beran.weather.service.TemperatureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class AppConfig {

  @Bean
  public Logger logger() {
    return LoggerFactory.getLogger(App.class);
  }

  @Bean
  public TemperatureService temperatureService(
      MongoTemplate mongoTemplate,
      TemperatureRepository temperatureRepository,
      SequenceService sequenceService) {
    return new TemperatureService(mongoTemplate, temperatureRepository, sequenceService);
  }

  @Bean
  public SequenceService sequenceService(SequenceRepository sequenceRepository) {
    return new SequenceService(sequenceRepository);
  }

  @Bean
  public CsvWeather csvWeather(
      CountryRepository countryRepository,
      CityRepository cityRepository,
      TemperatureRepository temperatureRepository,
      SequenceService sequenceService) {
    return new CsvWeather(
        countryRepository, cityRepository, temperatureRepository, sequenceService);
  }
}
