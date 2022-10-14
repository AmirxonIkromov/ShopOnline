package uz.pdp.internetmagazin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.internetmagazin.payload.UserDTO;
import uz.pdp.internetmagazin.service.UserEditService;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController{

    private final UserEditService userEditService;

    @Override
    public ResponseEntity<?> edit(Integer id, UserDTO userDTO) {
        return userEditService.edit(id,userDTO);
    }

    @Override
    public ResponseEntity<?>  getAll() {
        return userEditService.getAll();
    }


}
