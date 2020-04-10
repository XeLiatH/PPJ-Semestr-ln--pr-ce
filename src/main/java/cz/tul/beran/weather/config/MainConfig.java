package cz.tul.beran.weather.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        PropertyConfig.class,
        MysqlConfig.class,
        DaoConfig.class
})
public class MainConfig {

}
