package cz.tul.beran.weather.model;

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
                }
            );
    }
}
