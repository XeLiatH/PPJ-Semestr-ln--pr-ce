package cz.tul.beran.weather;

import cz.tul.beran.weather.model.CountryDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {

    SpringApplication app = new SpringApplication(Application.class);
    ApplicationContext ctx = app.run(args);

    // todo: for testing purposes only
    CountryDao countryDao = ctx.getBean(CountryDao.class);
    System.out.println(countryDao.getCountries());
  }
}
