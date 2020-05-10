package cz.tul.beran.weather.entity.mongo;

import com.fasterxml.jackson.annotation.JsonFormat;
import cz.tul.beran.weather.dto.importer.CsvWeatherRow;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Document(collection = "temperature-data")
public class Temperature {

  public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  @Id private Long id;

  @NotBlank private String countryCode;
  @NotBlank private String cityName;
  @NotNull private Double temperature;

  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @Indexed(expireAfterSeconds = 1209600)
  private Date createdAt;

  public static Temperature createFromCsvRow(CsvWeatherRow row) {
    Temperature temperature = new Temperature();

    temperature.setCountryCode(row.getCountryCode());
    temperature.setCityName(row.getCityName());
    temperature.setTemperature(row.getTemperature());
    temperature.setCreatedAt(row.getCreatedAt());

    return temperature;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public Double getTemperature() {
    return temperature;
  }

  public void setTemperature(Double temperature) {
    this.temperature = temperature;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
