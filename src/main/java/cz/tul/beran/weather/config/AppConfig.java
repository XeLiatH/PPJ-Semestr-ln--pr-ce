package cz.tul.beran.weather.config;

import cz.tul.beran.weather.App;
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
  public TemperatureService temperatureService(MongoTemplate mongoTemplate) {
    return new TemperatureService(mongoTemplate);
  }
}
