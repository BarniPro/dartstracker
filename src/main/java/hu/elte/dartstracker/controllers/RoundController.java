package hu.elte.dartstracker.controllers;

import hu.elte.dartstracker.entities.Match;
import hu.elte.dartstracker.entities.Round;
import hu.elte.dartstracker.repositories.MatchRepository;
import hu.elte.dartstracker.repositories.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class RoundController {
    @Autowired
    private RoundRepository roundRepository;

    protected <Round> Optional<Round> getEntity(Long id) {
        return (Optional<Round>) roundRepository.findById(id);
    }

    protected void deleteEntity(Long Id) {
        roundRepository.deleteById(Id);
    }

    protected Round saveEntity(Round t) {
        return roundRepository.save(t);
    }

    @GetMapping("/competitions/{competition_id}/matches/{match_id}/rounds")
    public ResponseEntity<Iterable<Round>> getAll(@PathVariable Long competition_id, @PathVariable Long match_id) {
        return new ResponseEntity(roundRepository.findByMatchId(match_id), HttpStatus.OK);
    }

    @GetMapping("/competitions/{competition_id}/matches/{match_id}/rounds/{id}")
    public ResponseEntity<Round> get(@PathVariable Long competition_id, @PathVariable Long match_id, @PathVariable Long id) {
        Optional<Round> optionalRound = roundRepository.findByIdAndMatchId(id, match_id);
        if (optionalRound.isPresent()) {
            return ResponseEntity.ok(optionalRound.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Autowired
    MatchRepository matchRepository;

    @Secured({ "ROLE_ADMIN", "ROLE_OFFICIAL" })
    @PostMapping("/competitions/{competition_id}/matches/{match_id}/rounds")
    public ResponseEntity<Round> post(@PathVariable Long competition_id, @PathVariable Long match_id, @RequestBody Round round) {
        Optional<Match> optionalMatch = matchRepository.findById(match_id);
        if (optionalMatch.isPresent()) {
            round.setMatch(optionalMatch.get());
            Round newRound = roundRepository.save(round);
            return ResponseEntity.ok(newRound);
        }
        return ResponseEntity.notFound().build();
    }

    @Secured({ "ROLE_ADMIN", "ROLE_OFFICIAL" })
    @DeleteMapping("/competitions/{competition_id}/matches/{match_id}/rounds/{id}")
    public ResponseEntity<Round> delete(@PathVariable Long competition_id, @PathVariable Long match_id, @PathVariable Long id) {
        Optional<Round> optionalRound = roundRepository.findByIdAndMatchId(id, match_id);
        if (optionalRound.isPresent()) {
            roundRepository.delete(optionalRound.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Secured({ "ROLE_ADMIN", "ROLE_OFFICIAL" })
    @PutMapping("/competitions/{competition_id}/matches/{match_id}/rounds/{id}")
    public ResponseEntity<Round> put(@PathVariable Long competition_id, @PathVariable Long match_id, @PathVariable Long id, @RequestBody Round round) {
        Optional<Round> optionalRound = roundRepository.findByIdAndMatchId(id, match_id);
        if (optionalRound.isPresent()) {
            round.setId(id);
            round.setMatch(matchRepository.findById(match_id).get());
            return ResponseEntity.ok(roundRepository.save(round));
        }
        return ResponseEntity.notFound().build();
    }

}
