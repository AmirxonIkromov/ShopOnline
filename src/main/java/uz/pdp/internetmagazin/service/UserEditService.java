package uz.pdp.internetmagazin.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.internetmagazin.payload.UserDTO;

public interface UserEditService {
    ResponseEntity<?> edit(Integer id, UserDTO userDTO);

    ResponseEntity<?>  getAll();
}
