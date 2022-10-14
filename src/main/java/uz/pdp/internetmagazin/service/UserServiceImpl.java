package uz.pdp.internetmagazin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.internetmagazin.entity.RoleEnum;
import uz.pdp.internetmagazin.entity.User;
import uz.pdp.internetmagazin.exception.InputDataExistsException;
import uz.pdp.internetmagazin.payload.ApiResult;
import uz.pdp.internetmagazin.payload.SignInDTO;
import uz.pdp.internetmagazin.payload.SignUpDTO;
import uz.pdp.internetmagazin.repository.RoleRepository;
import uz.pdp.internetmagazin.repository.UserRepository;
import uz.pdp.internetmagazin.security.JwtTokenProvider;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.objectMapper = objectMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(value -> (UserDetails) value).orElseGet(() -> (UserDetails) ResponseEntity.status(HttpStatus.CONFLICT).body(username + "is not found"));
    }

    @Value("${app.jwtExpirationInMs}")
    private long accessTokenDate;

    @Override
    public ResponseEntity<?> signUp(SignUpDTO reqSignUp) {

        Optional<User> byUsername = userRepository.findByUsername(reqSignUp.getUsername());
        if (byUsername.isEmpty()) {
            if (!reqSignUp.getUsername().isEmpty() && !reqSignUp.getFullName().isEmpty() && !reqSignUp.getPassword().isEmpty()) {
                User user = new User();
                user.setFullName(reqSignUp.getFullName());
                user.setUsername(reqSignUp.getUsername());
                user.setRole(roleRepository.findByName(RoleEnum.ROLE_USER));
                user.setPassword(passwordEncoder.encode(reqSignUp.getPassword()));
                userRepository.save(user);
                return ResponseEntity.ok(ApiResult.successResponce("User success"));
            }else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResult.failResponse("User malumot toliq berilmadi!", HttpStatus.CONFLICT.value()));
            }

        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResult.failResponse("Username mavjud", HttpStatus.CONFLICT.value()));
        }
    }

    @Override
    public ResponseEntity<?> signIn(SignInDTO reqSignIn) {
        ObjectNode data = objectMapper.createObjectNode();
        userRepository.findByUsername(reqSignIn.getUsername()).orElseThrow(() -> new InputDataExistsException("Username mavjud emas"));


        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(reqSignIn.getUsername(), reqSignIn.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        String refreshToken = jwtTokenProvider.refreshToken(authentication);
        data.put("accessToken", jwt);
        data.put("refreshToken", refreshToken);
        data.put("tokenType", "Bearer ");
        data.put("expiryDate", accessTokenDate);

        return ResponseEntity.ok(ApiResult.successResponce(data));
    }

    public UserDetails loadUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User id not found: " + userId));
    }
}
