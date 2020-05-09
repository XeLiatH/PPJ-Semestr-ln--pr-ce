package cz.tul.beran.weather.dto.mysql;

import javax.validation.constraints.NotBlank;

public class CountryDTO {

  @NotBlank private String code;
  @NotBlank private String name;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
