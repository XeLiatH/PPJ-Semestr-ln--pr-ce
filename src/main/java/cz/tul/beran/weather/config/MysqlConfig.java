package cz.tul.beran.weather.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class MysqlConfig {

  @Bean
  @Autowired
  public DataSource mysqlDataSource(MysqlProperties mysqlProperties) {

    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName(mysqlProperties.getDriver());
    dataSource.setUrl(mysqlProperties.getUrl());
    dataSource.setUsername(mysqlProperties.getUsername());
    dataSource.setPassword(mysqlProperties.getPassword());

    return dataSource;
  }
}
