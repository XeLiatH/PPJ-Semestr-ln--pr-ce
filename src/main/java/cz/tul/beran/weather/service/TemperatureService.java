package cz.tul.beran.weather.service;

import com.mongodb.DBObject;
import cz.tul.beran.weather.entity.mongo.Temperature;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TemperatureService {

  private final MongoTemplate mongoTemplate;

  public TemperatureService(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public DBObject getAverageTemperatureByCityNameInLastNDays(
      String cityName, Integer daysAgo) {

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
