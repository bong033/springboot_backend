package org.example.service;

import org.example.dto.RequestResponse;
import org.example.exception.PasswordBlankException;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    // signs up a user and stores the user to the Users table
    public RequestResponse signUp(RequestResponse registrationRequest){

        RequestResponse requestResponse = new RequestResponse();

        User user = new User();

        user.setName(registrationRequest.getName());

        user.setEmail(registrationRequest.getEmail());

        if(registrationRequest.getPassword().isBlank())
            throw new PasswordBlankException();

        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setRole(registrationRequest.getRole());
        User usersResult = userRepository.save(user);

        if (usersResult != null && usersResult.getUserId()>0) {
            requestResponse.setUser(usersResult);
            requestResponse.setMessage("User saved successfully.");
        }

        return requestResponse;
    }

    // logs in the authenticated user
    public RequestResponse signIn(RequestResponse signinRequest){

        RequestResponse requestResponse = new RequestResponse();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),signinRequest.getPassword()));

        var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow();

        var jwt = jwtUtils.generateToken(user);

        var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);

        requestResponse.setToken(jwt);
        requestResponse.setRefreshToken(refreshToken);
        requestResponse.setExpirationTime("24Hr");
        requestResponse.setMessage("Signed in successfully.");
        requestResponse.setRole(user.getRole());

        return requestResponse;
    }

    // refresh a token for an authenticated user
    public RequestResponse refreshToken(RequestResponse refreshTokenRequest){

        RequestResponse requestResponse = new RequestResponse();

        String ourEmail = jwtUtils.extractUsername(refreshTokenRequest.getToken());

        User user = userRepository.findByEmail(ourEmail).orElseThrow();

        if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtUtils.generateToken(user);
            requestResponse.setToken(jwt);
            requestResponse.setRefreshToken(refreshTokenRequest.getToken());
            requestResponse.setExpirationTime("24Hr");
            requestResponse.setMessage("Refreshed token successfully.");
        }

        return requestResponse;
    }

    // returns the user information
    public RequestResponse profile(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(authentication); //prints the details of the user(name,email,password,roles e.t.c)
        System.out.println(authentication.getDetails()); // prints the remote ip
        System.out.println(authentication.getName()); //prints the EMAIL, the email was stored as the unique identifier

        var user = new User();
        user = userRepository.findByEmail(authentication.getName()).orElseThrow();

        RequestResponse requestResponse = new RequestResponse();
        requestResponse.setName(user.getName());
        requestResponse.setEmail(user.getEmail());
        requestResponse.setPassword(user.getPassword());

        return requestResponse;

    }

}
