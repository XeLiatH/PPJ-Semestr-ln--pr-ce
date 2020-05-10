package cz.tul.beran.weather.controller.rest;

import cz.tul.beran.weather.entity.mongo.Temperature;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class TemperatureControllerTest extends AbstractTest {

  @Before
  public void setUp() {
    super.setUp();
  }

  @Test
  public void getTemperatures_ok() throws Exception {
    String url = "/temperatures";

    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.APPLICATION_JSON)).andReturn();

    int status = mvcResult.getResponse().getStatus();
    Assert.assertEquals(200, status);

    String content = mvcResult.getResponse().getContentAsString();
    Temperature[] temperatures = super.mapFromJson(content, Temperature[].class);

    Assert.assertTrue(temperatures.length > 0);
  }
}
