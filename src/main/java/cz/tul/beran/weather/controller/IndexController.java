package cz.tul.beran.weather.controller;

import cz.tul.beran.weather.entity.mongo.Temperature;
import cz.tul.beran.weather.entity.mysql.Country;
import cz.tul.beran.weather.repository.mongo.TemperatureRepository;
import cz.tul.beran.weather.repository.mysql.CountryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class IndexController {

  private final TemperatureRepository temperatureRepository;
  private final CountryRepository countryRepository;

  public IndexController(
      TemperatureRepository temperatureRepository, CountryRepository countryRepository) {
    this.temperatureRepository = temperatureRepository;
    this.countryRepository = countryRepository;
  }

  @GetMapping(path = "")
  public String index(Model model) {

    model.addAttribute("countries", countryRepository.findAll());

    return "index";
  }

  @GetMapping(path = "/table/{id}")
  public String table(@PathVariable Long id, Model model) {

    Country country = countryRepository.findById(id).orElse(null);
    if (country == null) {
      return "index";
    }

    List<Temperature> temperatureList = temperatureRepository.findByCountryCode(country.getCode());

    model.addAttribute("temperatures", temperatureList);

    return "table";
  }
}
