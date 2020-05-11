package cz.tul.beran.weather.controller.rest;

import cz.tul.beran.weather.dto.mysql.CityDTO;
import cz.tul.beran.weather.entity.mysql.City;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class CityControllerTest extends AbstractTest {

  @Before
  public void setUp() {
    super.setUp();
  }

  @Test
  @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:populate.sql")
  @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:truncate.sql")
  public void getCitiesOk() throws Exception {
    String url = "/cities";

    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON)).andReturn();

    int status = mvcResult.getResponse().getStatus();
    Assert.assertEquals(200, status);

    String content = mvcResult.getResponse().getContentAsString();
    City[] cities = super.mapFromJson(content, City[].class);

    Assert.assertTrue(cities.length > 0);
  }

  @Test
  @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:populate.sql")
  @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:truncate.sql")
  public void getCityOk() throws Exception {
    String url = "/cities/1";

    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON)).andReturn();

    int status = mvcResult.getResponse().getStatus();
    Assert.assertEquals(200, status);

    String content = mvcResult.getResponse().getContentAsString();
    City city = super.mapFromJson(content, City.class);

    Assert.assertNotNull(city);
  }

  @Test
  @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:countries.sql")
  @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:truncate.sql")
  public void createCityOk() throws Exception {
    String url = "/cities";

    CityDTO dto = new CityDTO();
    dto.setName("Plzeň");
    dto.setCountryId(1L);

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
  @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:countries.sql")
  @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:truncate.sql")
  public void createCityBad() throws Exception {
    String url = "/cities";

    CityDTO dto = new CityDTO();
    dto.setName("Plzeň");

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
  @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:populate.sql")
  @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:truncate.sql")
  public void updateCityOk() throws Exception {
    String url = "/cities/1";

    CityDTO dto = new CityDTO();
    dto.setName("Plzeň");
    dto.setCountryId(1L);

    String input = super.mapToJson(dto);

    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(input))
            .andReturn();

    int status = mvcResult.getResponse().getStatus();
    Assert.assertEquals(200, status);
  }

  @Test
  @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:populate.sql")
  @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:truncate.sql")
  public void updateCityBad() throws Exception {
    String url = "/cities/1";

    CityDTO dto = new CityDTO();
    dto.setCountryId(1L);

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
  @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:populate.sql")
  @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:truncate.sql")
  public void deleteCityOk() throws Exception {
    String url = "/cities/1";

    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(url)).andReturn();

    int status = mvcResult.getResponse().getStatus();
    Assert.assertEquals(200, status);
  }
}
