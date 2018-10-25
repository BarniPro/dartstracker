package hu.elte.dartstracker.repositories;

import hu.elte.dartstracker.entities.Round;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoundRepository extends CrudRepository<Round, Long> {
    List<Round> findByMatchId(Long matchId);
    Optional<Round> findByIdAndMatchId(Long id, Long match_id);
}

