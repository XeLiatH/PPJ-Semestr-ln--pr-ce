package cz.tul.beran.weather.service.api;

import com.google.gson.Gson;
import cz.tul.beran.weather.dto.OpenWeatherDTO;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class OpenWeatherApi implements WeatherProvider {

  @Value("${cz.tul.beran.weather.apiKey}")
  private String apiKey;

  @Value("${cz.tul.beran.weather.apiUrl}")
  private String apiUrl;

  private final Logger logger;

  public OpenWeatherApi(Logger logger) {
    this.logger = logger;
  }

  public OpenWeatherDTO getWeatherData(String country, String city) {

    try {
      country = URLEncoder.encode(country, "utf-8");
      city = URLEncoder.encode(city, "utf-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    String query = city + "," + country;

    UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl);

    uriComponentsBuilder.queryParam("q", query).queryParam("appid", apiKey);

    String requestUri = uriComponentsBuilder.toUriString();
    logger.info(String.format("Requesting weather data [%s]", requestUri));

    RestTemplate restTemplate = new RestTemplate();

    String jsonResponse = restTemplate.getForObject(requestUri, String.class);

    return new Gson().fromJson(jsonResponse, OpenWeatherDTO.class);
  }
}
