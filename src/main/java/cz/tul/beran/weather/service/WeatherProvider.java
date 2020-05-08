package cz.tul.beran.weather.service;

import cz.tul.beran.weather.dto.WeatherDTO;

public interface WeatherProvider {

  WeatherDTO getWeatherData(String country, String city);
}
