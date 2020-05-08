package cz.tul.beran.weather.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "cz.tul.beran.weather.repository.mongo")
@EnableJpaRepositories(basePackages = "cz.tul.beran.weather.repository.mysql")
public class DatabaseConfig {}
