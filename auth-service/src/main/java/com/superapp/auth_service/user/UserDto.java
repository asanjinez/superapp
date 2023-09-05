package com.superapp.auth_service.user;

import com.superapp.auth_service.user.role.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class UserDto {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String lastname;
    private String role;
}
