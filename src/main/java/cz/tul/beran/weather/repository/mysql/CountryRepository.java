package cz.tul.beran.weather.repository.mysql;

import cz.tul.beran.weather.entity.mysql.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

  Country findByCodeEquals(String code);
}
