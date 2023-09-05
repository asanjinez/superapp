package com.superapp.auth_service.auth;

import com.superapp.auth_service.config.JwtService;
import com.superapp.auth_service.exception.InvalidCredentialsException;
import com.superapp.auth_service.user.IUserService;
import com.superapp.auth_service.user.User;
import com.superapp.auth_service.user.UserDto;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class AuthenticationServiceImpl implements IAuthenticationService{
    @Autowired
    private IUserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse register(UserDto userDto){
        try {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

            User user = userService.createUser(userDto);
            String jwtToken = jwtService.generateToken(user);

            return new AuthenticationResponse(jwtToken);
        } catch (Exception e) {
            log.error("Error registering user: {}", userDto.getUsername());
            throw e;
        }
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(authenticationRequest.toAuthenticationToken());

            User user = userService.findByUsername(authenticationRequest.getUsername());
            String jwtToken = jwtService.generateToken(user);
            return new AuthenticationResponse(jwtToken);
        } catch (BadCredentialsException e){
            log.error("Error authenticating user: {}", authenticationRequest.getUsername());
            throw new InvalidCredentialsException("Invalid credentials");

        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw e;
        }

    }
}
