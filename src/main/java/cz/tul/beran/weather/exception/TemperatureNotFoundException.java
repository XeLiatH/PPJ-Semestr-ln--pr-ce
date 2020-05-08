package cz.tul.beran.weather.exception;

public class TemperatureNotFoundException extends RuntimeException {
  public TemperatureNotFoundException(Long id) {
    super(String.format("Temperate not found [id = %d]", id));
  }
}
