package com.JokeApp.Project.service;

import com.JokeApp.Project.model.UserJoke;
import com.JokeApp.Project.repository.UserJokesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserJokesService {
    private final UserJokesRepository userJokesRepository;

    public UserJokesService(UserJokesRepository userJokesRepository){
        this.userJokesRepository = userJokesRepository;
    }

    public List<UserJoke> getUserJokesByUserId(Long userId){
        List<UserJoke> userJokes = userJokesRepository.findAllUserJokes(userId);
        return userJokes;
    }
}
