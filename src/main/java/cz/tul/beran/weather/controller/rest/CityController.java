package cz.tul.beran.weather.controller.rest;

import cz.tul.beran.weather.dto.mysql.CityDTO;
import cz.tul.beran.weather.entity.mysql.City;
import cz.tul.beran.weather.service.mysql.CityService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CityController {

  private final CityService cityService;

  public CityController(CityService cityService) {
    this.cityService = cityService;
  }

  @GetMapping("/cities")
  List<City> all() {
    return cityService.findAll();
  }

  @GetMapping("/cities/{id}")
  City one(@PathVariable Long id) {
    return cityService.findById(id);
  }

  @PostMapping(value = "/cities", consumes = "application/json")
  City newCity(@Valid @RequestBody CityDTO cityDTO) {
    return cityService.create(cityDTO);
  }

  @PutMapping("/cities/{id}")
  City updateCity(@PathVariable Long id, @Valid @RequestBody CityDTO cityDTO) {
    return cityService.update(id, cityDTO);
  }

  @DeleteMapping("/cities/{id}")
  void deleteCity(@PathVariable Long id) {
    cityService.deleteById(id);
  }
}
