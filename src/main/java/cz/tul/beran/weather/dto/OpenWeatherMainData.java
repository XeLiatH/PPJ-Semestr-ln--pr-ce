package cz.tul.beran.weather.dto;

public class OpenWeatherMainData {

  double temp;

  public OpenWeatherMainData(double temp) {
    this.temp = temp;
  }

  public double getTemp() {
    return temp;
  }
}
