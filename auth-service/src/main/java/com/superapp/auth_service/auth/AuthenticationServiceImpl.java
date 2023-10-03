package com.superapp.auth_service.auth;

import com.superapp.auth_service.config.JwtService;
import com.superapp.auth_service.exception.InvalidCredentialsException;
import com.superapp.auth_service.user.IUserService;
import com.superapp.auth_service.user.User;
import com.superapp.auth_service.user.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;


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
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public AuthenticationResponse register(UserDto userDto){
        try {
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

            User user = userService.createUser(userDto);
            String jwtToken = jwtService.generateToken(user);
            log.info("User registered: {}", userDto.getUsername());
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
            log.info("User authenticated: {}", authenticationRequest.getUsername());
            return new AuthenticationResponse(jwtToken);
        } catch (BadCredentialsException e) {
            log.error("Error authenticating user: {}", authenticationRequest.getUsername());
            throw new InvalidCredentialsException("Invalid credentials");

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

        @Override
        public String validateToken(String token){
            try {
                String username = jwtService.extractUsername(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                String idUser = String.valueOf(userService.findByUsername(username).getId());

                return jwtService.validateToken(token) ? idUser : null;
            } catch (Exception e) {
                log.error(e.getMessage(),e);
                throw e;
            }
    }
}
