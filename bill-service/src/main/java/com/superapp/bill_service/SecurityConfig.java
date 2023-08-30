package com.superapp.bill_service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

//    @Bean
//    UserDetailsService userDetailsService(PasswordEncoder encoder) {
////        UserDetails adminUser = User.withUsername("admin")
////                .password(encoder.encode("password"))
////                .roles("ADMIN")
////                .build();
////
////        UserDetails user = User.withUsername("user1")
////                .password(encoder.encode("password"))
////                .roles("USER")
////                .build();
//
////        return new  InMemoryUserDetailsManager(adminUser, user);
//
//        return new UserInfoUserDetailsService();
//    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests().requestMatchers("/**").permitAll()
                .and()
//                .authorizeHttpRequests().requestMatchers("/person").permitAll()
//                .and().formLogin().and()
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService(passwordEncoder()));
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }

}
