package cz.tul.beran.weather.provisioning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.util.List;

public class Provisioner {

  @Autowired private NamedParameterJdbcOperations jdbc;

  @Autowired private DataSource dataSource;

  public void doProvision() {

    List<String> tables =
        jdbc.getJdbcOperations()
            .queryForList("select table_name from information_schema.tables", String.class);

    if (!tables.contains("country")) {
      createDb();

      tables =
          jdbc.getJdbcOperations()
              .queryForList("select table_name from information_schema.tables", String.class);

      System.out.println(tables);
    }
  }

  public void createDb() {
    Resource rc = new ClassPathResource("create_tables.hsql");

    try {
      ScriptUtils.executeSqlScript(dataSource.getConnection(), rc);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}
