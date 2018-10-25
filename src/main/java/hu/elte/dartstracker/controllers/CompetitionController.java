package hu.elte.dartstracker.controllers;

import hu.elte.dartstracker.entities.Competition;
import hu.elte.dartstracker.entities.User;
import hu.elte.dartstracker.repositories.CompetitionRepository;
import hu.elte.dartstracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("competitions")
public class CompetitionController {
    @Autowired
    private CompetitionRepository competitionRepository;

    protected <Competition> Optional<Competition> getEntity(Long id){
        return (Optional<Competition>) competitionRepository.findById(id);
    }

    protected void deleteEntity(Long Id){
        competitionRepository.deleteById(Id);
    }

    protected Competition saveEntity(Competition t){
        return competitionRepository.save(t);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Competition>> getAll() {
        return new ResponseEntity(competitionRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Competition> get(@PathVariable Long id) {
        Optional<Competition> optionalCompetition = competitionRepository.findById(id);
        if (optionalCompetition.isPresent()) {
            return ResponseEntity.ok(optionalCompetition.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Secured({ "ROLE_ADMIN" })
    @PostMapping("")
    public ResponseEntity<Competition> post(@RequestBody Competition competition) {
        Competition newCompetition = competitionRepository.save(competition);
        return ResponseEntity.ok(newCompetition);
    }

    @Secured({ "ROLE_ADMIN" })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Competition> optionalCompetition = competitionRepository.findById(id);
        if (optionalCompetition.isPresent()) {
            deleteEntity(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Secured({ "ROLE_ADMIN" })
    @PutMapping("/{id}")
    public ResponseEntity<Competition> put(@PathVariable Long id, @RequestBody Competition competition) {
        Optional<Competition> optionalCompetition = competitionRepository.findById(id);
        if (optionalCompetition.isPresent()) {
            competition.setId(id);
            return ResponseEntity.ok(competitionRepository.save(competition));
        }
        return ResponseEntity.notFound().build();
    }

    @Autowired
    UserRepository userRepository;

    @Secured({ "ROLE_ADMIN" })
    @PostMapping("/{id}/officials")
    public ResponseEntity<User> addOfficial(
            @PathVariable Long id,
            @RequestBody User official){

        Optional<Competition> oCompetitions = getEntity(id);
        if(oCompetitions.isPresent()){
            User newOfficial = userRepository.save(official);
            oCompetitions.get().getOfficials().add(newOfficial);
            saveEntity(oCompetitions.get());
            return ResponseEntity.ok(newOfficial);
        }
        return ResponseEntity.notFound().build();
    }

    @Secured({ "ROLE_ADMIN" })
    @GetMapping("/{id}/officials")
    public ResponseEntity<User> getOfficials(
            @PathVariable Long id){

        Optional<Competition> oCompetitions = getEntity(id);
        if(oCompetitions.isPresent()){
            return new ResponseEntity(userRepository.findByCompetitionsId(id), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/officials/{official_id}")
    public ResponseEntity<User> deleteOfficial(
            @PathVariable Long id,
            @PathVariable Long official_id){

        Optional<Competition> oCompetitions = getEntity(id);
        if(oCompetitions.isPresent()){
            Optional<User> newOfficial = userRepository.findById(official_id);
            if (newOfficial.isPresent()) {
                oCompetitions.get().getOfficials().remove(newOfficial.get());
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }
}
