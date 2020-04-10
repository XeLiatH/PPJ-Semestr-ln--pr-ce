package cz.tul.beran.weather.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import({
        PropertyConfig.ProdProperties.class,
        PropertyConfig.DevProperties.class,
        PropertyConfig.TestProperties.class
})
public class PropertyConfig {

    @Configuration
    @PropertySource("classpath:app.properties")
    @Profile("prod")
    public static class ProdProperties {

    }

    @Configuration
    @PropertySource("classpath:app_dev.properties")
    @Profile("dev")
    public static class DevProperties {

    }

    @Configuration
    @PropertySource("classpath:app_test.properties")
    @Profile("test")
    public static class TestProperties {

    }
}
