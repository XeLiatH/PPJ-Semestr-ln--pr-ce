package cz.tul.beran.weather.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CsvWeatherRow {

  private String countryCode;
  private String cityName;
  private double temperature;
  private Date createdAt;

  public CsvWeatherRow(String countryCode, String cityName, double temperature, Date createdAt) {
    this.countryCode = countryCode;
    this.cityName = cityName;
    this.temperature = temperature;
    this.createdAt = createdAt;
  }

  public CsvWeatherRow(String[] parts) throws ParseException {
    this.countryCode = parts[0];
    this.cityName = parts[1];
    this.temperature = Double.parseDouble(parts[2]);
    this.createdAt = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(parts[3]);
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public double getTemperature() {
    return temperature;
  }

  public void setTemperature(double temperature) {
    this.temperature = temperature;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
