package cz.tul.beran.weather.dto.provider;

public class OpenWeatherDTO implements WeatherDTO {

  private final OpenWeatherMainData main;

  public OpenWeatherDTO(OpenWeatherMainData main) {
    this.main = main;
  }

  public OpenWeatherMainData getMain() {
    return main;
  }
}
