package com.JokeApp.Project.controller;
import com.JokeApp.Project.model.UserJoke;
import com.JokeApp.Project.service.UserJokesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user-jokes")
public class UserJokesController {
    private final UserJokesService userJokesService;

    public UserJokesController(UserJokesService userJokesService) {
        this.userJokesService = userJokesService;
    }

    @GetMapping("/{userId}")
    public List<UserJoke> getUserJokesByUserId(@PathVariable Long userId ){
        List<UserJoke> userJokes = userJokesService.getUserJokesByUserId(userId);
        return userJokes;
    }
}