package cz.tul.beran.weather.controller.rest;

import cz.tul.beran.weather.entity.mongo.Temperature;
import cz.tul.beran.weather.exception.TemperatureNotFoundException;
import cz.tul.beran.weather.repository.mongo.TemperatureRepository;
import cz.tul.beran.weather.service.SequenceService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TemperatureController {

  private final TemperatureRepository temperatureRepository;
  private final SequenceService sequenceService;

  public TemperatureController(
      TemperatureRepository temperatureRepository, SequenceService sequenceService) {
    this.temperatureRepository = temperatureRepository;
    this.sequenceService = sequenceService;
  }

  @GetMapping("/temperatures")
  List<Temperature> all() {
    return temperatureRepository.findAll();
  }

  @GetMapping("/temperatures/{id}")
  Temperature one(@PathVariable Long id) {
    return temperatureRepository
        .findById(id)
        .orElseThrow(() -> new TemperatureNotFoundException(id));
  }

  @PostMapping("/temperatures")
  Temperature newTemperature(@Valid @RequestBody Temperature temperature) {
    long id = sequenceService.getNextId();
    temperature.setId(id);

    return temperatureRepository.save(temperature);
  }

  @PutMapping("/temperatures/{id}")
  Temperature updateTemperature(
      @Valid @RequestBody Temperature newTemperature, @PathVariable Long id) {
    return temperatureRepository
        .findById(id)
        .map(
            temperature -> {
              temperature.setCityName(newTemperature.getCityName());
              temperature.setCountryCode(newTemperature.getCountryCode());
              temperature.setTemperature(newTemperature.getTemperature());
              temperature.setCreatedAt(newTemperature.getCreatedAt());
              return temperatureRepository.save(temperature);
            })
        .orElseGet(
            () -> {
              newTemperature.setId(id);
              return temperatureRepository.save(newTemperature);
            });
  }

  @DeleteMapping("/temperatures/{id}")
  void deleteTemperature(@PathVariable Long id) {
    temperatureRepository.deleteById(id);
  }
}
