package cz.tul.beran.weather.schedule;

import cz.tul.beran.weather.dto.provider.WeatherDTO;
import cz.tul.beran.weather.entity.mongo.Temperature;
import cz.tul.beran.weather.entity.mysql.City;
import cz.tul.beran.weather.entity.mysql.Country;
import cz.tul.beran.weather.repository.mysql.CountryRepository;
import cz.tul.beran.weather.service.mongo.TemperatureService;
import cz.tul.beran.weather.service.provider.WeatherProvider;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UpdateTemperatures {

  private final WeatherProvider weatherProvider;
  private final CountryRepository countryRepository;
  private final TemperatureService temperatureService;

  public UpdateTemperatures(
      WeatherProvider weatherProvider,
      CountryRepository countryRepository,
      TemperatureService temperatureService) {
    this.weatherProvider = weatherProvider;
    this.countryRepository = countryRepository;
    this.temperatureService = temperatureService;
  }

  @Scheduled(cron = "${cz.tul.beran.weather.interval}")
  public void fetch() {

    for (Country country : countryRepository.findAll()) {
      for (City city : country.getCities()) {

        String countryCode = country.getCode();
        String cityName = city.getName();

        WeatherDTO dto = weatherProvider.getWeatherData(countryCode, cityName);
        Double temperature = dto.getMain().getTemp();

        Temperature tempObj = new Temperature();
        tempObj.setCountryCode(countryCode);
        tempObj.setCityName(countryCode);

        temperatureService.createTemperature(countryCode, cityName, temperature);
      }
    }
  }
}
