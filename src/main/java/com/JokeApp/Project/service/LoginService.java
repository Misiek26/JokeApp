package com.JokeApp.Project.service;

import com.JokeApp.Project.model.User;
import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Getter
@Setter
public class LoginService {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String signInUser(String email, String password) {

        Optional<User> user = userService.getUserByEmail(email);
        String token = "";

        if(user.isPresent()){
            User userWithPassedEmail = user.get();
            String encodePasswordFromDB = userWithPassedEmail.getPassword();

            boolean passwordIsValid = bCryptPasswordEncoder.matches(password, encodePasswordFromDB);

            if(!passwordIsValid){
                throw new IllegalStateException("Password is incorrect for this user");
            }

            token = UUID.randomUUID().toString();
        }
        return token;
    }
}
