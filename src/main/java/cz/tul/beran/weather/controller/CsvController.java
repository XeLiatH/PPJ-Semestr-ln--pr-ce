package cz.tul.beran.weather.controller;

import cz.tul.beran.weather.service.CsvWeather;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CsvController {

  private final CsvWeather csvWeather;

  public CsvController(CsvWeather csvWeather) {
    this.csvWeather = csvWeather;
  }

  @PostMapping(value = "/import", consumes = "multipart/form-data")
  public RedirectView importData(@RequestParam("file") MultipartFile file) {
    csvWeather.importData(file);
    return new RedirectView("/");
  }

  @GetMapping(value = "/export/{id}")
  public ResponseEntity<Resource> exportData(@PathVariable Long id) {

    String exportedData = csvWeather.exportData(id);
    String fileName = "export.csv";

    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
    headers.add("Pragma", "no-cache");
    headers.add("Expires", "0");

    ByteArrayResource resource = new ByteArrayResource(exportedData.getBytes());

    return ResponseEntity.ok()
        .headers(headers)
        .contentLength(exportedData.length())
        .contentType(MediaType.parseMediaType("application/octet-stream"))
        .body(resource);
  }
}
