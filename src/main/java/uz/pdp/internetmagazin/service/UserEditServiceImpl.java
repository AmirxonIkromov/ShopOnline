package uz.pdp.internetmagazin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.internetmagazin.config.SecurityConfig;
import uz.pdp.internetmagazin.entity.User;
import uz.pdp.internetmagazin.payload.ApiResult;
import uz.pdp.internetmagazin.payload.UserDTO;
import uz.pdp.internetmagazin.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserEditServiceImpl implements UserEditService{

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;
    private final SecurityConfig securityConfig;

    @Override
    public ResponseEntity<?> edit(Integer id, UserDTO userDTO) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            user.setFullName(userDTO.getFullName());
            user.setPassword(encoder.encode(userDTO.getPassword()));
            user.setUsername(userDTO.getUsername());

            userRepository.save(user);

            return ResponseEntity.ok(ApiResult.successResponce("User edited"));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResult.failResponse( "User not found", HttpStatus.CONFLICT.value()));
    }

    @Override
    public ResponseEntity<?>  getAll() {
        User currentUser = securityConfig.getCurrentUser();
        Integer id = currentUser.getId();
        if (id == 1 || id == 2) {
            List<User> all = userRepository.findAll();
            return ResponseEntity.ok(ApiResult.successResponce(all));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiResult.failResponse("Not Found forbiddin", HttpStatus.FORBIDDEN.value()));
    }
}
