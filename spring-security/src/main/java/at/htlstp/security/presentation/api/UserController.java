package at.htlstp.security.presentation.api;

import at.htlstp.security.domain.UserEntity;
import at.htlstp.security.persistence.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public record UserController(UserRepository userRepository) {

    @GetMapping
    UserEntity getSelf(Principal principal) {
        var name = principal.getName();
        return userRepository
                .findByUsername(name)
                .orElseThrow();
    }

    @PostMapping
    UserEntity save(@RequestBody UserEntity user) {
        return userRepository.save(user);
    }
}
