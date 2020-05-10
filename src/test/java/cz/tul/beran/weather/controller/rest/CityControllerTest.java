package cz.tul.beran.weather.controller.rest;

import cz.tul.beran.weather.dto.mysql.CityDTO;
import cz.tul.beran.weather.dto.mysql.CountryDTO;
import cz.tul.beran.weather.entity.mysql.City;
import cz.tul.beran.weather.service.mysql.CountryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class CityControllerTest extends AbstractTest {

  @Mock CountryService countryService;

  @Before
  public void setUp() {
    super.setUp();
  }

  @Test
  @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:test_data.sql")
  public void getCities_ok() throws Exception {
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
  public void createCity_ok() throws Exception {
    CountryDTO countryDTO = new CountryDTO();
    countryDTO.setName("Czech Republic");
    countryDTO.setCode("CZ");
    countryService.create(countryDTO);

    String url = "/cities";

    CityDTO dto = new CityDTO();
    dto.setName("Liberec");
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
}
