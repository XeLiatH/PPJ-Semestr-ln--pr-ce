package cz.tul.beran.weather.service.mysql;

import cz.tul.beran.weather.dto.mysql.CountryDTO;
import cz.tul.beran.weather.entity.mysql.Country;
import cz.tul.beran.weather.exception.CountryNotFoundException;
import cz.tul.beran.weather.repository.mysql.CountryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountryService {

  private final CountryRepository countryRepository;

  public CountryService(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  public List<Country> findAll() {
    return (List<Country>) countryRepository.findAll();
  }

  public Country findById(Long id) {
    return countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException(id));
  }

  public Country create(CountryDTO countryDTO) {
    Country country = new Country();
    country.setCode(countryDTO.getCode());
    country.setName(countryDTO.getName());

    return countryRepository.save(country);
  }

  public Country update(Long id, CountryDTO countryDTO) {
    return countryRepository
        .findById(id)
        .map(
            country -> {
              country.setName(countryDTO.getName());
              country.setCode(countryDTO.getCode());
              return countryRepository.save(country);
            })
        .orElseGet(
            () -> {
              Country country = new Country();
              country.setId(id);
              country.setCode(countryDTO.getCode());
              country.setName(countryDTO.getName());
              return countryRepository.save(country);
            });
  }

  public void deleteById(Long id) {
    countryRepository.deleteById(id);
  }
}
