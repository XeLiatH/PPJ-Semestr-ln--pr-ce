package cz.tul.beran.weather.model;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.sql.ResultSet;
import java.util.List;

public class CountryDao {

  private final NamedParameterJdbcOperations jdbc;

  public CountryDao(NamedParameterJdbcOperations jdbc) {
    this.jdbc = jdbc;
  }

  public List<Country> getCountries() {
    return jdbc.query(
        "select * from country",
        (ResultSet rs, int row) -> {
          Country country = new Country();
          country.setId(rs.getInt("id"));
          country.setName(rs.getString("name"));
          country.setCode(rs.getString("code"));

          return country;
        });
  }

  public boolean createCountry(Country country) {
    BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(country);

    return jdbc.update("insert into country (name, code) values (:name, :code)", params) == 1;
  }

  public boolean updateCountry(Country country) {
    BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(country);

    return jdbc.update("update country set name=:name, code=:code where id=:id", params) == 1;
  }

  public boolean deleteCountry(Country country) {
      BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(country);

      return jdbc.update("delete from country where id=:id", params) == 1;
  }
}
