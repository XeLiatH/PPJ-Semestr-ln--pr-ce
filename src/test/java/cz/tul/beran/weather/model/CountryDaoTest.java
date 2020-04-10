package cz.tul.beran.weather.model;

import cz.tul.beran.weather.config.MainConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainConfig.class)
@ActiveProfiles("test")
public class CountryDaoTest {

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private DataSource dataSource;

    @Test
    public void test1_createCountry() {
        NamedParameterJdbcOperations jdbc = new NamedParameterJdbcTemplate(dataSource);

        jdbc.getJdbcOperations().execute("delete from country");
        jdbc.getJdbcOperations().execute("delete from city");

        Country country = new Country("CZ", "Czech republic");

        Assert.assertTrue("Country creation should return true.", countryDao.createCountry(country));
    }

    @Test
    public void test2_retrieveCountry() {
        List<Country> countries = countryDao.getCountries();

        Assert.assertEquals("Expected 1 country in database after test1.", 1, countries.size());
    }
}
