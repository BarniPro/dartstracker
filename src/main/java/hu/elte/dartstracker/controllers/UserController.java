package hu.elte.dartstracker.controllers;

import hu.elte.dartstracker.entities.User;
import hu.elte.dartstracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    protected <User> Optional<User> getEntity(Long id){
        return (Optional<User>) userRepository.findById(id);
    }

    protected void deleteEntity(Long Id){
        userRepository.deleteById(Id);
    }

    protected User saveEntity(User t){
        return userRepository.save(t);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<User>> getAll() {
        return new ResponseEntity(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Secured({ "ROLE_ADMIN" })
    @PostMapping("")
    public ResponseEntity<User> post(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userRepository.save(user);
        return ResponseEntity.ok(newUser);
    }

    @Secured({ "ROLE_ADMIN" })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            deleteEntity(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Secured({ "ROLE_ADMIN" })
    @PutMapping("/{id}")
    public ResponseEntity<User> put(@PathVariable Long id, @RequestBody User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            user.setId(id);
            user.setPassword(user.getPassword());
            return ResponseEntity.ok(userRepository.save(user));
        }
        return ResponseEntity.notFound().build();
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody User user) {
        Optional<User> oUser = userRepository.findByUsername(user.getUsername());
        if (oUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(User.Role.ROLE_PLAYER);
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody String username) {
        Optional<User> oUser = userRepository.findByUsername(username);
        if (oUser.isPresent()) {
            return ResponseEntity.ok(oUser.get());
        }
        return ResponseEntity.ok().build();
    }

}
