package cz.tul.beran.weather.config;

import cz.tul.beran.weather.service.city.CityService;
import cz.tul.beran.weather.service.country.CountryService;
import cz.tul.beran.weather.service.temperature.TemperatureService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfig {

    @Bean
    public CityService cityService() {
        return new CityService();
    }

    @Bean
    public CountryService countryService() {
        return new CountryService();
    }

    @Bean
    public TemperatureService temperatureService() {
        return new TemperatureService();
    }
}
