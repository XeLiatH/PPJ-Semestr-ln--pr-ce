package cz.tul.beran.weather.service.mysql;

import cz.tul.beran.weather.dto.mysql.CountryDTO;
import cz.tul.beran.weather.entity.mysql.Country;
import cz.tul.beran.weather.exception.CountryNotFoundException;
import cz.tul.beran.weather.exception.ReadOnlyException;
import cz.tul.beran.weather.repository.mysql.CountryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountryService {

  @Value("${read-only:false}")
  private Boolean readOnly;

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
    if (readOnly) {
      throw new ReadOnlyException();
    }

    Country country = new Country();
    country.setCode(countryDTO.getCode());
    country.setName(countryDTO.getName());

    return countryRepository.save(country);
  }

  public Country update(Long id, CountryDTO countryDTO) {
    if (readOnly) {
      throw new ReadOnlyException();
    }

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
    if (readOnly) {
      throw new ReadOnlyException();
    }

    countryRepository.deleteById(id);
  }
}
