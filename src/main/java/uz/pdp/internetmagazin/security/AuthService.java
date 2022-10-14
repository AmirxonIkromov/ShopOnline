package uz.pdp.internetmagazin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.internetmagazin.entity.User;
import uz.pdp.internetmagazin.repository.UserRepository;

import java.util.Optional;


@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(value -> (UserDetails) value).orElseGet(() -> (UserDetails) ResponseEntity.status(HttpStatus.CONFLICT)
                .body(username + "is not found"));
    }

    public UserDetails loadUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User id not found: " +userId));
    }
}
