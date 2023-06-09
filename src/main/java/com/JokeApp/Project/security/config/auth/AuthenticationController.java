package com.JokeApp.Project.security.config.auth;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/register/form")
    public ResponseEntity<AuthenticationResponse> registerForm(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String email,
            @RequestParam String password
    ) {
        var user = RegisterRequest
                .builder()
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .password(password)
                .build();
        return ResponseEntity.ok(service.register(user));
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam String token){
        return service.confirmToken(token);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/authenticate/form")
    public RedirectView authenticateForm(
            @RequestParam String email,
            @RequestParam String password,
            HttpServletResponse response) throws IOException, InterruptedException {
        var authUser = AuthenticationRequest
                .builder()
                .email(email)
                .password(password)
                .build();

        String token = service.authenticate(authUser).getToken();

        String url = "http://localhost:8080/authenticate";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> httpResponse = restTemplate.exchange(
                url, HttpMethod.GET, requestEntity, String.class);

        RedirectView redirectView = new RedirectView("http://localhost:8080");
        return redirectView;
    }
}
