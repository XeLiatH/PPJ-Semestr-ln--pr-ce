package cz.tul.beran.weather.config;

import cz.tul.beran.weather.model.CityDao;
import cz.tul.beran.weather.model.CountryDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

  @Bean
  public CityDao cityDao() {
    return new CityDao();
  }

  @Bean
  public CountryDao countryDao() {
    return new CountryDao();
  }
}
