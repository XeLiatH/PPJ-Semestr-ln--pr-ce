package cz.tul.beran.weather.model;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.sql.ResultSet;
import java.util.List;

public class CityDao {

  private final NamedParameterJdbcOperations jdbc;

  public CityDao(NamedParameterJdbcOperations jdbc) {
    this.jdbc = jdbc;
  }

  public List<City> getCities() {
    return jdbc.query(
        "select country.id as cid, country.name as cname, country.code as ccode, city.id as id, city.name as name from city, country",
        (ResultSet rs, int row) -> {
          Country country = new Country();
          country.setId(rs.getInt("cid"));
          country.setName(rs.getString("cname"));
          country.setCode(rs.getString("ccode"));

          City city = new City();
          city.setId(rs.getInt("id"));
          city.setName(rs.getString("name"));
          city.setCountry(country);

          return city;
        });
  }

  public boolean createCity(City city) {
    BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(city);

    return jdbc.update("insert into city (name) values (:name)", params) == 1;
  }

  public boolean updateCity(City city) {
      BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(city);

      return jdbc.update("update city set name=:name where id=:id", params) == 1;
  }

  public boolean deleteCity(City city) {
      BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(city);

      return jdbc.update("delete from offer where id=:id", params) == 1;
  }
}
