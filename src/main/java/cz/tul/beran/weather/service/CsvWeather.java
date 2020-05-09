package cz.tul.beran.weather.service;

import cz.tul.beran.weather.dto.CsvWeatherRow;
import cz.tul.beran.weather.entity.mongo.Temperature;
import cz.tul.beran.weather.entity.mysql.Country;
import cz.tul.beran.weather.exception.CountryNotFoundException;
import cz.tul.beran.weather.repository.mongo.TemperatureRepository;
import cz.tul.beran.weather.repository.mysql.CityRepository;
import cz.tul.beran.weather.repository.mysql.CountryRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CsvWeather {

  private static final String COMMA_DELIMITER = ",";
  private static final Integer EXPECTED_COLUMNS = 4;

  private final CountryRepository countryRepository;
  private final CityRepository cityRepository;
  private final TemperatureRepository temperatureRepository;
  private final SequenceService sequenceService;

  public CsvWeather(
      CountryRepository countryRepository,
      CityRepository cityRepository,
      TemperatureRepository temperatureRepository,
      SequenceService sequenceService) {
    this.countryRepository = countryRepository;
    this.cityRepository = cityRepository;
    this.temperatureRepository = temperatureRepository;
    this.sequenceService = sequenceService;
  }

  public void importData(MultipartFile file) throws IllegalArgumentException {
    if (null == file) {
      throw new IllegalArgumentException("Imported csv can not be null");
    }

    try {
      String contents = new String(file.getBytes());
      List<CsvWeatherRow> rows = parseCsv(contents);

      for (CsvWeatherRow row : rows) {
        if (null == countryRepository.findByCodeEquals(row.getCountryCode())) {
          continue;
        }

        if (null == cityRepository.findByNameEquals(row.getCityName())) {
          continue;
        }

        Long id = sequenceService.getNextId();
        temperatureRepository.save(Temperature.createFromCsvRow(id, row));
      }

    } catch (IOException | ParseException e) {
      throw new IllegalArgumentException("Imported csv is not valid");
    }
  }

  public String exportData(Long countryId) {
    Country country = countryRepository.findById(countryId).orElse(null);
    if (null == country) {
      throw new CountryNotFoundException(countryId);
    }

    List<Temperature> temperatures = temperatureRepository.findByCountryCode(country.getCode());

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    StringBuilder stringBuilder = new StringBuilder();
    for (Temperature temperature : temperatures) {
      stringBuilder.append(
          String.format(
              "%s,%s,%f,%s\n\r",
              temperature.getCountryCode(),
              temperature.getCityName(),
              temperature.getTemperature(),
              dateFormat.format(temperature.getCreatedAt())));
    }

    return stringBuilder.toString();
  }

  private List<CsvWeatherRow> parseCsv(String contents) throws ParseException {

    String[] rows = contents.split(System.getProperty("line.separator"));
    List<CsvWeatherRow> result = new ArrayList<>();

    for (String row : rows) {

      String[] rowParts = row.split(COMMA_DELIMITER);
      if (rowParts.length == EXPECTED_COLUMNS) {
        result.add(new CsvWeatherRow(rowParts));
      }
    }

    return result;
  }
}
