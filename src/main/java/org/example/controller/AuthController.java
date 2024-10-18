package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.RequestResponse;
import org.example.repository.UserRepository;
import org.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/api")
@CrossOrigin("*")   // Allow requests to load resources on other servers. https://spring.io/guides/gs/rest-service-cors
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    UserRepository userRepository;

    //register for an account
    @PostMapping("/signup")
    public ResponseEntity<RequestResponse> signUp(@Valid @RequestBody RequestResponse signUpRequest){
        // Check if the user already exists
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            // Return a response indicating that the signup already exist
            return ResponseEntity.status(HttpStatus.IM_USED)
                    .body(new RequestResponse());
        }

        // Proceed with the signup process
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signUp(signUpRequest));
    }

    //signin to an account
    @PostMapping("/signin")
    public ResponseEntity<RequestResponse> signIn(@RequestBody RequestResponse signInRequest){
        return ResponseEntity.status(HttpStatus.OK).body(authService.signIn(signInRequest));
    }

    //refresh a token
    @PostMapping("/refresh")
    public ResponseEntity<RequestResponse> refreshToken(@RequestBody RequestResponse refreshTokenRequest){
        return ResponseEntity.status(HttpStatus.OK).body(authService.refreshToken(refreshTokenRequest));
    }
}
