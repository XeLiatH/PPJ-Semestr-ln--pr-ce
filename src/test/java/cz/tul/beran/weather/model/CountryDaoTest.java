package cz.tul.beran.weather.model;

import cz.tul.beran.weather.config.ApplicationConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@ActiveProfiles("test")
public class CountryDaoTest {

  @Autowired private CountryDao countryDao;

  @Test
  public void test1_createCountry() {
    countryDao.deleteCountries();

    Country country = new Country("CZ", "Czech republic");

    Assert.assertTrue("Country creation should return true.", countryDao.createCountry(country));
  }

  @Test
  public void test2_retrieveCountry() {
    List<Country> countries = countryDao.getCountries();

    Assert.assertEquals("Expected 1 country in database after test1.", 1, countries.size());
  }
}
