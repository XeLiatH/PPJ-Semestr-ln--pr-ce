package cz.tul.beran.weather.controller.rest;

import cz.tul.beran.weather.entity.mongo.Temperature;
import cz.tul.beran.weather.repository.mongo.SequenceRepository;
import cz.tul.beran.weather.repository.mongo.TemperatureRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TemperatureControllerTest extends AbstractTest {

  @Autowired TemperatureRepository temperatureRepository;

  @Autowired SequenceRepository sequenceRepository;

  @Before
  public void setUp() {
    super.setUp();
  }

  @Test
  public void createTemperatureOk() throws Exception {
    temperatureRepository.deleteAll();
    sequenceRepository.deleteAll();

    String url = "/temperatures";

    Temperature temperature = new Temperature();
    temperature.setTemperature(200.3);
    temperature.setCountryCode("CZ");
    temperature.setCityName("Liberec");
    temperature.setCreatedAt(new Date());

    String input = super.mapToJson(temperature);

    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(input))
            .andReturn();

    int status = mvcResult.getResponse().getStatus();
    Assert.assertEquals(200, status);

    String content = mvcResult.getResponse().getContentAsString();
    Temperature temp = super.mapFromJson(content, Temperature.class);

    Assert.assertNotNull(temp);
  }

  @Test
  public void getTemperaturesOk() throws Exception {
    String url = "/temperatures";

    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON))
            .andReturn();

    int status = mvcResult.getResponse().getStatus();
    Assert.assertEquals(200, status);

    String content = mvcResult.getResponse().getContentAsString();
    Temperature[] temperatures = super.mapFromJson(content, Temperature[].class);

    Assert.assertTrue(temperatures.length > 0);
  }

  @Test
  public void getTemperatureOk() throws Exception {
    String url = "/temperatures/1";

    MvcResult mvcResult =
        mvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON))
            .andReturn();

    int status = mvcResult.getResponse().getStatus();
    Assert.assertEquals(200, status);

    String content = mvcResult.getResponse().getContentAsString();
    Temperature temperature = super.mapFromJson(content, Temperature.class);

    Assert.assertNotNull(temperature);
  }

  @Test
  public void createTemperatureBad() throws Exception {
    String url = "/temperatures";

    Temperature temperature = new Temperature();
    temperature.setTemperature(200.3);
    temperature.setCreatedAt(new Date());

    String input = super.mapToJson(temperature);

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
  public void editTemperatureOk() throws Exception {
    String url = "/temperatures/1";

    Temperature temperature = new Temperature();
    temperature.setTemperature(180.3);
    temperature.setCountryCode("CZ");
    temperature.setCityName("Liberec");
    temperature.setCreatedAt(new Date());

    String input = super.mapToJson(temperature);

    MvcResult mvcResult =
        mvc.perform(
                MockMvcRequestBuilders.put(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(input))
            .andReturn();

    int status = mvcResult.getResponse().getStatus();
    Assert.assertEquals(200, status);

    String content = mvcResult.getResponse().getContentAsString();
    Temperature temp = super.mapFromJson(content, Temperature.class);

    Assert.assertNotNull(temp);
  }

  @Test
  public void editTemperatureBad() throws Exception {
    String url = "/temperatures/1";

    Temperature temperature = new Temperature();
    temperature.setTemperature(180.3);
    temperature.setCreatedAt(new Date());

    String input = super.mapToJson(temperature);

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
  public void removeTemperatureOk() throws Exception {
    String url = "/temperatures/1";

    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(url)).andReturn();

    int status = mvcResult.getResponse().getStatus();
    Assert.assertEquals(200, status);
  }
}
