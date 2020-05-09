package cz.tul.beran.weather.dto.mysql;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CityDTO {

  @NotBlank private String name;
  @NotNull private Long countryId;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getCountryId() {
    return countryId;
  }

  public void setCountryId(Long countryId) {
    this.countryId = countryId;
  }
}
