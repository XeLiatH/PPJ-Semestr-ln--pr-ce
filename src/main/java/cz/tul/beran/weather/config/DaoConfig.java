package cz.tul.beran.weather.config;

import cz.tul.beran.weather.model.CityDao;
import cz.tul.beran.weather.model.CountryDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

@Configuration
public class DaoConfig {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public DaoConfig(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Bean
    public CityDao cityDao() {
        return new CityDao(namedParameterJdbcOperations);
    }

    @Bean
    public CountryDao countryDao() {
        return new CountryDao(namedParameterJdbcOperations);
    }
}
