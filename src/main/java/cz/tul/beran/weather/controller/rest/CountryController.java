package cz.tul.beran.weather.controller.rest;

import cz.tul.beran.weather.entity.mysql.Country;
import cz.tul.beran.weather.exception.CountryNotFoundException;
import cz.tul.beran.weather.repository.mysql.CountryRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CountryController {

  private final CountryRepository countryRepository;

  public CountryController(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  @GetMapping("/countries")
  List<Country> all() {
    return (List<Country>) countryRepository.findAll();
  }

  @GetMapping("/countries/{id}")
  Country one(@PathVariable Long id) {
    return countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
  }

  @PostMapping("/countries")
  Country newCountry(@Valid @RequestBody Country country) {
    return countryRepository.save(country);
  }

  @PutMapping("/countries/{id}")
  Country updateCountry(@Valid @RequestBody Country newCountry, @PathVariable Long id) {
    return countryRepository
        .findById(id)
        .map(
            country -> {
              country.setName(newCountry.getName());
              country.setCode(newCountry.getCode());
              return countryRepository.save(country);
            })
        .orElseGet(
            () -> {
              newCountry.setId(id);
              return countryRepository.save(newCountry);
            });
  }

  @DeleteMapping("/countries/{id}")
  void deleteCountry(@PathVariable Long id) {
    countryRepository.deleteById(id);
  }
}
