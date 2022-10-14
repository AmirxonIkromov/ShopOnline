package uz.pdp.internetmagazin.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import uz.pdp.internetmagazin.payload.SignInDTO;
import uz.pdp.internetmagazin.payload.SignUpDTO;

public interface UserService {
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	ResponseEntity<?> signUp(SignUpDTO reqSignUp);
	ResponseEntity<?>  signIn(SignInDTO reqSignIn);
}
