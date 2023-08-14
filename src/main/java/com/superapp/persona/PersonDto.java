package com.superapp.persona;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class PersonDto {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String lastname;
    private EType type;
}
