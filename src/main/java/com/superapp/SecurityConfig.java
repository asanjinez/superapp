package com.superapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll() // Permite el acceso sin autenticación a todas las páginas
                .and()
                .csrf().disable() // Deshabilita la protección CSRF (esto también se recomienda solo para pruebas)
                .formLogin(); // Habilita el formulario de login por defecto
    }
}


