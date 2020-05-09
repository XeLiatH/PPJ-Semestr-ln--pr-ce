package cz.tul.beran.weather.service;

import cz.tul.beran.weather.entity.mongo.Sequence;
import cz.tul.beran.weather.repository.mongo.SequenceRepository;

public class SequenceService {

  private final SequenceRepository sequenceRepository;

  public SequenceService(SequenceRepository sequenceRepository) {
    this.sequenceRepository = sequenceRepository;
  }

  public Long getNextId() {
    Sequence seq =
        sequenceRepository
            .findById((long) 1)
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
