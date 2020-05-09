package cz.tul.beran.weather.service.mysql;

import cz.tul.beran.weather.dto.mysql.CityDTO;
import cz.tul.beran.weather.entity.mysql.City;
import cz.tul.beran.weather.entity.mysql.Country;
import cz.tul.beran.weather.exception.CityNotFoundException;
import cz.tul.beran.weather.exception.CountryNotFoundException;
import cz.tul.beran.weather.repository.mysql.CityRepository;
import cz.tul.beran.weather.repository.mysql.CountryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityService {

  private final CityRepository cityRepository;
  private final CountryRepository countryRepository;

  public CityService(CityRepository cityRepository, CountryRepository countryRepository) {
    this.cityRepository = cityRepository;
    this.countryRepository = countryRepository;
  }

  public List<City> findAll() {
    return (List<City>) cityRepository.findAll();
  }

  public City findById(Long id) {
    return cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));
  }

  public City create(CityDTO cityDTO) {

    City city = new City();
    city.setName(cityDTO.getName());
    city.setCountry(getCountry(cityDTO.getCountryId()));

    return cityRepository.save(city);
  }

  public City update(Long id, CityDTO cityDTO) {
    Country country = getCountry(cityDTO.getCountryId());

    return cityRepository
        .findById(id)
        .map(
            city -> {
              city.setName(cityDTO.getName());
              city.setCountry(country);
              return cityRepository.save(city);
            })
        .orElseGet(
            () -> {
              City city = new City();
              city.setId(id);
              city.setCountry(country);
              city.setName(cityDTO.getName());

              return cityRepository.save(city);
            });
  }

  public void deleteById(Long id) {
    cityRepository.deleteById(id);
  }

  private Country getCountry(Long id) {
    Country country = countryRepository.findById(id).orElse(null);
    if (null == country) {
      throw new CountryNotFoundException(id);
    }

    return country;
  }
}
