package cz.tul.beran.weather.exception;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(Long id) {
        super(String.format("City not found [id = %d]", id));
    }
}
