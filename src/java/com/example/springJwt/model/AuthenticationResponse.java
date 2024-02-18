package com.example.springJwt.model;

public class AuthenticationResponse {
    private String token;
    private String message;
   // private String jwt;
   // private String message;
 //   private String accessLevel;
    public AuthenticationResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }
}
