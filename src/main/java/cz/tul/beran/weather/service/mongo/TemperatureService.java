package cz.tul.beran.weather.service.mongo;

import com.mongodb.DBObject;
import cz.tul.beran.weather.entity.mongo.Temperature;
import cz.tul.beran.weather.exception.ReadOnlyException;
import cz.tul.beran.weather.exception.TemperatureNotFoundException;
import cz.tul.beran.weather.repository.mongo.TemperatureRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class TemperatureService {

  @Value("${read-only:false}")
  private Boolean readOnly;

  private final MongoTemplate mongoTemplate;

  private final TemperatureRepository temperatureRepository;
  private final SequenceService sequenceService;

  public TemperatureService(
      MongoTemplate mongoTemplate,
      TemperatureRepository temperatureRepository,
      SequenceService sequenceService) {
    this.mongoTemplate = mongoTemplate;
    this.temperatureRepository = temperatureRepository;
    this.sequenceService = sequenceService;
  }

  public List<Temperature> findAll() {
    return temperatureRepository.findAll();
  }

  public Temperature findById(Long id) {
    return temperatureRepository
        .findById(id)
        .orElseThrow(() -> new TemperatureNotFoundException(id));
  }

  public List<Temperature> findByCountryCode(String countryCode) {
    return temperatureRepository.findByCountryCode(countryCode);
  }

  public Temperature create(Temperature temperature) {
    if (readOnly) {
      throw new ReadOnlyException();
    }

    long id = sequenceService.getNextId();
    temperature.setId(id);

    return temperatureRepository.save(temperature);
  }

  public Temperature update(Long id, Temperature newTemperature) {
    if (readOnly) {
      throw new ReadOnlyException();
    }

    return temperatureRepository
        .findById(id)
        .map(
            temperature -> {
              temperature.setCityName(newTemperature.getCityName());
              temperature.setCountryCode(newTemperature.getCountryCode());
              temperature.setTemperature(newTemperature.getTemperature());
              temperature.setCreatedAt(newTemperature.getCreatedAt());
              return temperatureRepository.save(temperature);
            })
        .orElseGet(
            () -> {
              newTemperature.setId(id);
              return temperatureRepository.save(newTemperature);
            });
  }

  public void deleteById(Long id) {
    if (readOnly) {
      throw new ReadOnlyException();
    }

    temperatureRepository.deleteById(id);
  }

  public DBObject getAverageTemperatureByCityNameInLastNDays(String cityName, Integer daysAgo) {

    Calendar calendar = new GregorianCalendar();
    calendar.add(Calendar.DAY_OF_YEAR, daysAgo * (-1));
    Date ago = calendar.getTime();

    MatchOperation matchOperation =
        Aggregation.match(Criteria.where("cityName").is(cityName).and("createdAt").gte(ago));

    ProjectionOperation projectionOperation =
        Aggregation.project("cityName", "temperature", "createdAt");

    GroupOperation groupOperation =
        Aggregation.group("cityName").avg("temperature").as("averageTemperature");

    Aggregation aggregation =
        Aggregation.newAggregation(matchOperation, projectionOperation, groupOperation);

    return mongoTemplate
        .aggregate(aggregation, Temperature.class, DBObject.class)
        .getUniqueMappedResult();
  }
}
