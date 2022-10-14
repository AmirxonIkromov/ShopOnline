package uz.pdp.internetmagazin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.internetmagazin.payload.UserDTO;

@RequestMapping("/user")
public interface UserController {



    @PutMapping("/{id}")
    ResponseEntity<?> edit(@PathVariable Integer id,
                           @RequestBody UserDTO userDTO);


//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping()
    ResponseEntity<?>  getAll();
}
