package cz.tul.beran.weather.config;

import cz.tul.beran.weather.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

  @Bean
  public Logger logger() {
    return LoggerFactory.getLogger(Application.class);
  }
}
