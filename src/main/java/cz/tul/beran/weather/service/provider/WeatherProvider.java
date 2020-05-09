package cz.tul.beran.weather.service.provider;

import cz.tul.beran.weather.dto.provider.WeatherDTO;

public interface WeatherProvider {

  WeatherDTO getWeatherData(String country, String city);
}
