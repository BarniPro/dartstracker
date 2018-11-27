package hu.elte.dartstracker.controllers;

import hu.elte.dartstracker.entities.Competition;
import hu.elte.dartstracker.entities.Match;
import hu.elte.dartstracker.repositories.CompetitionRepository;
import hu.elte.dartstracker.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
public class MatchController {
    @Autowired
    private MatchRepository matchRepository;

    protected <Match> Optional<Match> getEntity(Long id){
        return (Optional<Match>) matchRepository.findById(id);
    }

    protected void deleteEntity(Long Id){
        matchRepository.deleteById(Id);
    }

    protected Match saveEntity(Match t){
        return matchRepository.save(t);
    }

    @GetMapping("/competitions/{competitionId}/matches")
    public ResponseEntity<Iterable<Match>> getAll(@PathVariable Long competitionId) {
        return new ResponseEntity(matchRepository.findAllByCompetitionId(competitionId), HttpStatus.OK);
    }

    @GetMapping("/competitions/{competitionId}/matches/{id}")
    public ResponseEntity<Match> get(@PathVariable Long id, @PathVariable Long competitionId) {
        Optional<Match> optionalMatch = matchRepository.findByIdAndCompetitionId(id, competitionId);
        if (optionalMatch.isPresent()) {
            return ResponseEntity.ok(optionalMatch.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Autowired
    CompetitionRepository competitionRepository;

    @Secured({ "ROLE_ADMIN", "ROLE_OFFICIAL" })
    @PostMapping("/competitions/{competitionId}/matches")
    public ResponseEntity<Match> post(@PathVariable Long competitionId, @RequestBody Match match) {
        Optional<Competition> optionalCompetition = competitionRepository.findById(competitionId);
        if (optionalCompetition.isPresent()) {
            match.setCompetition(optionalCompetition.get());
            Match newMatch = matchRepository.save(match);
            return ResponseEntity.ok(newMatch);
        }
        return ResponseEntity.notFound().build();
    }

    @Secured({ "ROLE_ADMIN", "ROLE_OFFICIAL" })
    @DeleteMapping("/competitions/{competitionId}/matches/{id}")
    public ResponseEntity delete(@PathVariable Long competitionId, @PathVariable Long id) {
        Optional<Match> optionalMatch = matchRepository.findByIdAndCompetitionId(id, competitionId);
        if (optionalMatch.isPresent()) {
            matchRepository.delete(optionalMatch.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Secured({ "ROLE_ADMIN", "ROLE_OFFICIAL" })
    @PutMapping("/competitions/{competitionId}/matches/{id}")
    public ResponseEntity<Match> post(@PathVariable Long competitionId, @PathVariable Long id, @RequestBody Match match) {
        Optional<Match> optionalMatch = matchRepository.findByIdAndCompetitionId(id, competitionId);
        if (optionalMatch.isPresent()) {
            match.setId(id);
            match.setCompetition(competitionRepository.findById(competitionId).get());
            return ResponseEntity.ok(matchRepository.save(match));
        }
        return ResponseEntity.notFound().build();
    }
}
