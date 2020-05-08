package cz.tul.beran.weather.controller.rest;

import cz.tul.beran.weather.entity.mongo.Sequence;
import cz.tul.beran.weather.entity.mongo.Temperature;
import cz.tul.beran.weather.exception.TemperatureNotFoundException;
import cz.tul.beran.weather.repository.mongo.SequenceRepository;
import cz.tul.beran.weather.repository.mongo.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TemperatureController {

  @Autowired private TemperatureRepository temperatureRepository;

  @Autowired private SequenceRepository sequenceRepository;

  @GetMapping("/temperatures")
  List<Temperature> all() {
    return temperatureRepository.findAll();
  }

  @GetMapping("/temperatures/{id}")
  Temperature one(@PathVariable Long id) throws Exception {
    return temperatureRepository
        .findById(id)
        .orElseThrow(() -> new TemperatureNotFoundException(id));
  }

  @PostMapping("/temperatures")
  Temperature newTemperature(@Valid @RequestBody Temperature temperature) {
    long id = getNextId();
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

  private Long getNextId() {
    Sequence seq =
        sequenceRepository
            .findById((long) 1)
            .orElseGet(
                () -> {
                  Sequence s = new Sequence();
                  s.setId(1);
                  s.setSeq(0);
                  return s;
                });

    seq.setSeq(seq.getSeq() + 1);
    sequenceRepository.save(seq);

    return seq.getSeq();
  }
}
