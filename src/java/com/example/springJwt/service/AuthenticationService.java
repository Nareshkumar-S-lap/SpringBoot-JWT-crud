package com.example.springJwt.service;


import com.example.springJwt.model.AuthResponseLogin;
import com.example.springJwt.model.AuthenticationResponse;
import com.example.springJwt.model.Role;
import com.example.springJwt.model.Token;
import com.example.springJwt.model.User;
import com.example.springJwt.repository.TokenRepository;
import com.example.springJwt.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final TokenRepository tokenRepository;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 TokenRepository tokenRepository,
                                 AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }

    public String register(User request) {

        // check if user already exist. if exist than authenticate the user
    	 if(repository.findByUsername(request.getUsername()).isPresent()) {
    	        return "User already exists";
    	    }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));


        user.setRole(request.getRole());

        user = repository.save(user);

        String jwt = jwtService.generateToken(user);

        saveUserToken(jwt, user);
        String responseMessage=null;
        
        if(user.getRole() == Role.ADMIN) {
            responseMessage = "Admin registration was successful. HI ADMIN!... you have access to all CRUD operations.";
        } 
        if(user.getRole() == Role.USER) {
            responseMessage = "User registration was successful. HI USER!... you have access only to read operations.";
        }

        // Include token in the response
        return  responseMessage;
    }

    public AuthResponseLogin authenticate(User request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            User user = repository.findByUsername(request.getUsername())
                    .orElseThrow();

            Role role = user.getRole();
            String jwt = jwtService.generateToken(user);

            String accessLevel = (role == Role.ADMIN) ?
                    "HI ADMIN... You have access to all CRUD operations" :
                    "HI USER... You have access to read operations only";

            return new AuthResponseLogin (jwt, "User login was successful", accessLevel);
        } catch (AuthenticationException e) {
            throw new Exception(e);
        }
    }


    private void revokeAllTokenByUser(User user) {
        List<Token> validTokens = tokenRepository.findAllTokensByUser(user.getId());
        if(validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t-> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }
    private void saveUserToken(String jwt, User user) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }
}
