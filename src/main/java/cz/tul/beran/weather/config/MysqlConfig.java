package cz.tul.beran.weather.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class MysqlConfig {

  @Value("${cz.tul.beran.weather.mysql.driver}")
  private String driver;

  @Value("${cz.tul.beran.weather.mysql.url}")
  private String url;

  @Value("${cz.tul.beran.weather.mysql.username}")
  private String username;

  @Value("${cz.tul.beran.weather.mysql.password}")
  private String password;

  @Bean
  public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
    return new NamedParameterJdbcTemplate(mysqlDataSource());
  }

  @Bean
  public DataSource mysqlDataSource() {

    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName(this.driver);
    dataSource.setUrl(this.url);
    dataSource.setUsername(this.username);
    dataSource.setPassword(this.password);

    return dataSource;
  }
}
