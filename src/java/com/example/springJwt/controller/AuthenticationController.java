package com.example.springJwt.controller;

import com.example.springJwt.model.AuthResponseLogin;
import com.example.springJwt.model.AuthenticationResponse;
import com.example.springJwt.model.User;
import com.example.springJwt.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody User request
            ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseLogin> login(
            @RequestBody User request
    ) throws Exception {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
