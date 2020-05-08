package cz.tul.beran.weather.repository.mysql;

import cz.tul.beran.weather.entity.mysql.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {}
