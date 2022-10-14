package uz.pdp.internetmagazin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.internetmagazin.payload.SignInDTO;
import uz.pdp.internetmagazin.payload.SignUpDTO;
import uz.pdp.internetmagazin.service.UserService;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody SignInDTO reqSignIn) {
        return ResponseEntity.ok(userService.signIn(reqSignIn));
    }

    @PostMapping("/create")
    public HttpEntity<?> signUp(@RequestBody SignUpDTO reqSignUp) {
        return ResponseEntity.ok(userService.signUp(reqSignUp));
    }
}
