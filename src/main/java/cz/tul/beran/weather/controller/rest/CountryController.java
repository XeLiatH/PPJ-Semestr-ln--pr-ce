package cz.tul.beran.weather.controller.rest;

import cz.tul.beran.weather.dto.mysql.CountryDTO;
import cz.tul.beran.weather.entity.mysql.Country;
import cz.tul.beran.weather.service.mysql.CountryService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CountryController {

  private final CountryService countryService;

  public CountryController(CountryService countryService) {
    this.countryService = countryService;
  }

  @GetMapping("/countries")
  List<Country> all() {
    return countryService.findAll();
  }

  @GetMapping("/countries/{id}")
  Country one(@PathVariable Long id) {
    return countryService.findById(id);
  }

  @PostMapping("/countries")
  Country newCountry(@Valid @RequestBody CountryDTO countryDTO) {
    return countryService.create(countryDTO);
  }

  @PutMapping("/countries/{id}")
  Country updateCountry(@PathVariable Long id, @Valid @RequestBody CountryDTO countryDTO) {
    return countryService.update(id, countryDTO);
  }

  @DeleteMapping("/countries/{id}")
  void deleteCountry(@PathVariable Long id) {
    countryService.deleteById(id);
  }
}
