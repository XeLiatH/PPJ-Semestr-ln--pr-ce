package cz.tul.beran.weather.dto.api;

public class OpenWeatherMainData {

  double temp;

  public OpenWeatherMainData(double temp) {
    this.temp = temp;
  }

  public double getTemp() {
    return temp;
  }

  public double getTempCelsius() {
    return temp - 273.15;
  }
}
