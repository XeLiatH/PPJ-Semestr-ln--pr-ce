package cz.tul.beran.weather.controller.rest;

import com.mongodb.DBObject;
import cz.tul.beran.weather.entity.mongo.Temperature;
import cz.tul.beran.weather.service.mongo.TemperatureService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TemperatureController {

  private final TemperatureService temperatureService;

  public TemperatureController(TemperatureService temperatureService) {
    this.temperatureService = temperatureService;
  }

  @GetMapping("/temperatures")
  List<Temperature> all() {
    return temperatureService.findAll();
  }

  @GetMapping("/temperatures/{cityName}/avg/{daysAgo}")
  DBObject average(@PathVariable String cityName, @PathVariable Integer daysAgo) {
    return temperatureService.getAverageTemperatureByCityNameInLastNDays(cityName, daysAgo);
  }

  @GetMapping("/temperatures/{id}")
  Temperature one(@PathVariable Long id) {
    return temperatureService.findById(id);
  }

  @PostMapping("/temperatures")
  Temperature newTemperature(@Valid @RequestBody Temperature temperature) {
    return temperatureService.create(temperature);
  }

  @PutMapping("/temperatures/{id}")
  Temperature updateTemperature(
      @PathVariable Long id, @Valid @RequestBody Temperature newTemperature) {
    return temperatureService.update(id, newTemperature);
  }

  @DeleteMapping("/temperatures/{id}")
  void deleteTemperature(@PathVariable Long id) {
    temperatureService.deleteById(id);
  }
}
