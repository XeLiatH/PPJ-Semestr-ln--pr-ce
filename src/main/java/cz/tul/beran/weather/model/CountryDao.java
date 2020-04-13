package cz.tul.beran.weather.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

public class CountryDao {

  @Autowired private NamedParameterJdbcOperations jdbc;

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
    HashMap<String, Object> params = new HashMap<>();

    params.put("name", country.getName());
    params.put("code", country.getCode());

    return jdbc.update("insert into country (name, code) values (:name, :code)", params) == 1;
  }

  public boolean updateCountry(Country country) {
    HashMap<String, Object> params = new HashMap<>();

    params.put("id", country.getId());
    params.put("name", country.getName());
    params.put("code", country.getCode());

    return jdbc.update("update country set name=:name, code=:code where id=:id", params) == 1;
  }

  public boolean deleteCountry(Country country) {
    HashMap<String, Object> params = new HashMap<>();

    params.put("id", country.getId());

    return jdbc.update("delete from country where id=:id", params) == 1;
  }

  public void deleteCountries() {
    jdbc.update("delete from country", new HashMap<>());
  }
}
