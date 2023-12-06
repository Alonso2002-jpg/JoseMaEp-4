package pe.isil.EPermanente4.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.isil.EPermanente4.exceptions.UserNotFound;
import pe.isil.EPermanente4.repository.users.UserRepository;
@Service
public class UserDetailsImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    public UserDetailsImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmailIgnoreCase(username).orElseThrow(() -> new UserNotFound("Usuario con email " + username + " no encontrado"));
    }
}
