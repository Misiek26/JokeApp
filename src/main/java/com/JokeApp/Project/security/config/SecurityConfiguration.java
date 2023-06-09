package com.JokeApp.Project.security.config;

import com.JokeApp.Project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf->csrf.disable())
            .authorizeHttpRequests(authz -> authz
                    .requestMatchers("/css/**", "/js/**", "/api/registration/**", "/api/auth/**", "/register").permitAll()
                    .anyRequest().authenticated())
                    .sessionManagement(sessionManagement ->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authenticationProvider(authenticationProvider)
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                    .formLogin(form->form
                            .loginPage("/login")
                            .permitAll()
                            .defaultSuccessUrl("/"));

        return http.build();
    }
}