package cz.tul.beran.weather.controller.rest;

import cz.tul.beran.weather.entity.mysql.City;
import cz.tul.beran.weather.exception.CityNotFoundException;
import cz.tul.beran.weather.repository.mysql.CityRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CityController {

  private final CityRepository cityRepository;

  public CityController(CityRepository cityRepository) {
    this.cityRepository = cityRepository;
  }

  @GetMapping("/cities")
  List<City> all() {
    return (List<City>) cityRepository.findAll();
  }

  @GetMapping("/cities/{id}")
  City one(@PathVariable Long id) {
    return cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));
  }

  @PostMapping("/cities")
  City newCity(@Valid @RequestBody City city) {
    return cityRepository.save(city);
  }

  @PutMapping("/cities/{id}")
  City updateCity(@Valid @RequestBody City newCity, @PathVariable Long id) {
    return cityRepository
        .findById(id)
        .map(
            city -> {
              city.setName(newCity.getName());
              city.setCountry(newCity.getCountry());
              return cityRepository.save(city);
            })
        .orElseGet(
            () -> {
              newCity.setId(id);
              return cityRepository.save(newCity);
            });
  }

  @DeleteMapping("/cities/{id}")
  void deleteCity(@PathVariable Long id) {
    cityRepository.deleteById(id);
  }
}
