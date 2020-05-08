package cz.tul.beran.weather.repository.mongo;

import cz.tul.beran.weather.entity.mongo.Sequence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SequenceRepository extends MongoRepository<Sequence, Long> {}
