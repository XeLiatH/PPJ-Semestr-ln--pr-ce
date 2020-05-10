package cz.tul.beran.weather.controller.rest;

import cz.tul.beran.weather.dto.mysql.CountryDTO;
import cz.tul.beran.weather.entity.mysql.Country;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CountryControllerTest extends AbstractTest {

  @PersistenceContext EntityManager entityManager;

  @Before
  public void setUp() {
    super.setUp();
  }

  @Test
  @Order(1)
  public void createCountry_ok() throws Exception {
    String url = "/countries";

    CountryDTO dto = new CountryDTO();
    dto.setName("Test");
    dto.setCode("te");

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
  @Order(2)
  public void getCountries_ok() throws Exception {
    String url = "/countries";

    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON)).andReturn();

    int status = mvcResult.getResponse().getStatus();
    Assert.assertEquals(200, status);

    String content = mvcResult.getResponse().getContentAsString();
    Country[] cities = super.mapFromJson(content, Country[].class);

    Assert.assertTrue(cities.length > 0);
  }
}
