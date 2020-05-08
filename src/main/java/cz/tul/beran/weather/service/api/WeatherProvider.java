package cz.tul.beran.weather.service.api;

import cz.tul.beran.weather.dto.WeatherDTO;

public interface WeatherProvider {

  WeatherDTO getWeatherData(String country, String city);
}
