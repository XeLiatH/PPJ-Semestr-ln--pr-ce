package cz.tul.beran.weather.service.mongo;

import cz.tul.beran.weather.entity.mongo.Sequence;
import cz.tul.beran.weather.repository.mongo.SequenceRepository;
import org.springframework.stereotype.Component;

@Component
public class SequenceService {

  private final SequenceRepository sequenceRepository;

  public SequenceService(SequenceRepository sequenceRepository) {
    this.sequenceRepository = sequenceRepository;
  }

  public Long getNextId() {
    Sequence seq =
        sequenceRepository
            .findById(1L)
            .orElseGet(
                () -> {
                  Sequence s = new Sequence();
                  s.setId(1);
                  s.setSeq(0);
                  return s;
                });

    seq.setSeq(seq.getSeq() + 1);
    sequenceRepository.save(seq);

    return seq.getSeq();
  }
}
