package cz.tul.beran.weather.schedule;

import cz.tul.beran.weather.entity.mongo.Temperature;
import cz.tul.beran.weather.repository.mongo.TemperatureRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class ExpireTemperatures {

  private final TemperatureRepository temperatureRepository;

  public ExpireTemperatures(TemperatureRepository temperatureRepository) {
    this.temperatureRepository = temperatureRepository;
  }

  @Scheduled(fixedRate = 10000)
  public void deleteAfterFourteenDays() {

    Calendar calendar = new GregorianCalendar();
    calendar.add(Calendar.DAY_OF_YEAR, -14);
    Date ago = calendar.getTime();

    List<Temperature> temperatures = temperatureRepository.findByCreatedAtBefore(ago);

    for (Temperature temperature : temperatures) {
      temperatureRepository.delete(temperature);
    }
  }
}
