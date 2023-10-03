package com.superapp.auth_service.auth;


import com.superapp.auth_service.user.UserDto;

public interface IAuthenticationService {
    public AuthenticationResponse register(UserDto userDto);
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
    public String validateToken(String token);
}
