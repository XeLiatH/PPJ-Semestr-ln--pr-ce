package cz.tul.beran.weather.config;

import cz.tul.beran.weather.model.CityDao;
import cz.tul.beran.weather.model.CountryDao;
import cz.tul.beran.weather.provisioning.Provisioner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ApplicationConfig {

  @Profile({"dev", "test"})
  @Bean(initMethod = "doProvision")
  public Provisioner provisioner() {
    return new Provisioner();
  }

  @Bean
  public CityDao cityDao() {
    return new CityDao();
  }

  @Bean
  public CountryDao countryDao() {
    return new CountryDao();
  }
}
