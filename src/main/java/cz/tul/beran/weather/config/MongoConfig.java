package cz.tul.beran.weather.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Autowired
    private MongoProperties properties;

    @Bean
    public MongoClient mongoClient() {
        return new MongoClient(properties.getHost());
    }
}
