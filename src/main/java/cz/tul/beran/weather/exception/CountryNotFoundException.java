package cz.tul.beran.weather.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(Long id) {
        super(String.format("Country not found [id = %d]", id));
    }
}
