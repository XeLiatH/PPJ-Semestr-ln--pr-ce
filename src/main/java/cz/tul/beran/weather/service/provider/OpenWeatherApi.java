package cz.tul.beran.weather.service.provider;

import com.google.gson.Gson;
import cz.tul.beran.weather.dto.provider.OpenWeatherDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Component
public class OpenWeatherApi implements WeatherProvider {

  private static final Logger logger = LoggerFactory.getLogger(OpenWeatherApi.class);

  @Value("${cz.tul.beran.weather.apiKey}")
  private String apiKey;

  @Value("${cz.tul.beran.weather.apiUrl}")
  private String apiUrl;

  @Override
  public OpenWeatherDTO getWeatherData(String country, String city) {

    try {
      country = URLEncoder.encode(country, "utf-8");
      city = URLEncoder.encode(city, "utf-8");
    } catch (UnsupportedEncodingException e) {
      logger.warn(e.getMessage(), e);
    }

    String query = city + "," + country;

    UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl);
    uriComponentsBuilder.queryParam("q", query).queryParam("appid", apiKey);

    String requestUri = uriComponentsBuilder.toUriString();

    logger.info(String.format("Requesting weather data [%s]", requestUri));

    RestTemplate restTemplate = new RestTemplate();
    String jsonResponse = restTemplate.getForObject(requestUri, String.class);

    logger.info(String.format("Data retrieved successfully [%s]", jsonResponse));

    return new Gson().fromJson(jsonResponse, OpenWeatherDTO.class);
  }
}
