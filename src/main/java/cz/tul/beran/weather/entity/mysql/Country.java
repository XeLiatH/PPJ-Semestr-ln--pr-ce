package cz.tul.beran.weather.entity.mysql;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "country")
public class Country {

  @Id
  @Column(name = "country_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "code", nullable = false, unique = true)
  @NotBlank
  private String code;

  @Column(name = "name", nullable = false)
  @NotBlank
  private String name;

  @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonIgnore
  private List<City> cities;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

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

  public List<City> getCities() {
    return cities;
  }

  public void setCities(List<City> cities) {
    this.cities = cities;
  }
}
