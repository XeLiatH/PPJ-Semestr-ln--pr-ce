package cz.tul.beran.weather.service.exporter;

import cz.tul.beran.weather.entity.mongo.Temperature;
import cz.tul.beran.weather.entity.mysql.Country;
import cz.tul.beran.weather.exception.CountryNotFoundException;
import cz.tul.beran.weather.repository.mysql.CountryRepository;
import cz.tul.beran.weather.service.mongo.TemperatureService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CsvTemperatureExporter implements TemperatureExporter {

  private final CountryRepository countryRepository;
  private final TemperatureService temperatureService;

  public CsvTemperatureExporter(
      CountryRepository countryRepository, TemperatureService temperatureService) {
    this.countryRepository = countryRepository;
    this.temperatureService = temperatureService;
  }

  @Override
  public String exportTemperatures(Long countryId) {

    Country country = countryRepository.findById(countryId).orElse(null);
    if (null == country) {
      throw new CountryNotFoundException(countryId);
    }

    List<Temperature> temperatures = temperatureService.findByCountryCode(country.getCode());

    StringBuilder stringBuilder = new StringBuilder();
    for (Temperature temperature : temperatures) {
      stringBuilder.append(
          String.format(
              "%s,%s,%f,%s\n\r",
              temperature.getCountryCode(),
              temperature.getCityName(),
              temperature.getTemperature(),
              Temperature.dateFormat.format(temperature.getCreatedAt())));
    }

    return stringBuilder.toString();
  }
}
