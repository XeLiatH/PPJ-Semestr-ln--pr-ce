package cz.tul.beran.weather;

import cz.tul.beran.weather.config.MainConfig;
import cz.tul.beran.weather.model.CountryDao;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class Main {

  public static void main(String[] args) {

    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    ConfigurableEnvironment env = ctx.getEnvironment();

    env.setActiveProfiles("prod");

    ctx.register(MainConfig.class);
    ctx.refresh();

    // todo: for testing purposes only
    CountryDao countryDao = ctx.getBean(CountryDao.class);
    System.out.println(countryDao.getCountries());
  }
}
