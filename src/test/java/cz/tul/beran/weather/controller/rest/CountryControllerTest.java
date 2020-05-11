package cz.tul.beran.weather.controller.rest;

import cz.tul.beran.weather.dto.mysql.CountryDTO;
import cz.tul.beran.weather.entity.mysql.Country;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class CountryControllerTest extends AbstractTest {

  @Before
  public void setUp() {
    super.setUp();
  }

  @Test
  @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:countries.sql")
  @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:truncate.sql")
  public void getCountriesOk() throws Exception {
    String url = "/countries";

    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON)).andReturn();

    int status = mvcResult.getResponse().getStatus();
    Assert.assertEquals(200, status);

    String content = mvcResult.getResponse().getContentAsString();
    Country[] countries = super.mapFromJson(content, Country[].class);

    Assert.assertTrue(countries.length > 0);
  }

  @Test
  @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:countries.sql")
  @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:truncate.sql")
  public void getCountryOk() throws Exception {
    String url = "/countries/1";

    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON)).andReturn();

    int status = mvcResult.getResponse().getStatus();
    Assert.assertEquals(200, status);

    String content = mvcResult.getResponse().getContentAsString();
    Country country = super.mapFromJson(content, Country.class);

    Assert.assertNotNull(country);
  }

  @Test
  @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:truncate.sql")
  public void createCountrOk() throws Exception {
    String url = "/countries";

    CountryDTO dto = new CountryDTO();
    dto.setName("Czech Republic");
    dto.setCode("CZ");

    String input = super.mapToJson(dto);

    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(input))
            .andReturn();

    int status = mvcResult.getResponse().getStatus();
    Assert.assertEquals(200, status);
  }

  @Test
  @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:truncate.sql")
  public void createCountryBad() throws Exception {
    String url = "/countries";

    CountryDTO dto = new CountryDTO();
    dto.setName("Czech Republic");

    String input = super.mapToJson(dto);

    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(input))
            .andReturn();

    int status = mvcResult.getResponse().getStatus();
    Assert.assertEquals(400, status);
  }

  @Test
  @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:countries.sql")
  @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:truncate.sql")
  public void updateCountryOk() throws Exception {
    String url = "/countries/1";

    CountryDTO dto = new CountryDTO();
    dto.setName("Czech Rep");
    dto.setCode("CZ");

    String input = super.mapToJson(dto);

    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(input))
            .andReturn();

    int status = mvcResult.getResponse().getStatus();
    Assert.assertEquals(200, status);

    String content = mvcResult.getResponse().getContentAsString();
    Country country = super.mapFromJson(content, Country.class);

    Assert.assertNotNull(country);
  }

  @Test
  @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:countries.sql")
  @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:truncate.sql")
  public void updateCountryBad() throws Exception {
    String url = "/countries/1";

    CountryDTO dto = new CountryDTO();
    dto.setName("Czech Republic");

    String input = super.mapToJson(dto);

    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(input))
            .andReturn();

    int status = mvcResult.getResponse().getStatus();
    Assert.assertEquals(400, status);
  }

  @Test
  @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:countries.sql")
  @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:truncate.sql")
  public void deleteCountryOk() throws Exception {
    String url = "/countries/1";

    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(url)).andReturn();

    int status = mvcResult.getResponse().getStatus();
    Assert.assertEquals(200, status);
  }
}
