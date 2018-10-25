package hu.elte.dartstracker.repositories;

import hu.elte.dartstracker.entities.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {
    List<Match> findAllByCompetitionId(Long competitionId);
    Optional<Match> findByIdAndCompetitionId(Long id, Long competitionId);
}
