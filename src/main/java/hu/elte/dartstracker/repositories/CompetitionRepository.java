package hu.elte.dartstracker.repositories;

import hu.elte.dartstracker.entities.Competition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends CrudRepository<Competition, Long> {
}
