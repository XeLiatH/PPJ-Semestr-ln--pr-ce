package cz.tul.beran.weather.service.importer;

import org.springframework.web.multipart.MultipartFile;

public interface TemperatureImporter {

  String COMMA_DELIMITER = ",";
  Integer EXPECTED_COLUMNS = 4;

  void importTemperatures(MultipartFile multipartFile);
}
