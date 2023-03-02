package at.htlstp.security.config.auth;

import at.htlstp.security.persistence.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public record UserDetailsServiceImpl(UserRepository userRepository) implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Unknown user/password combination"));
        return new UserDetailsImpl(user);
    }
}
