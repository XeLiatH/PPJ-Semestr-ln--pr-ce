package cz.tul.beran.weather.dto.provider;

public class OpenWeatherMainData {

  Double temp;

  public OpenWeatherMainData(Double temp) {
    this.temp = temp;
  }

  public Double getTemp() {
    return temp;
  }
}
