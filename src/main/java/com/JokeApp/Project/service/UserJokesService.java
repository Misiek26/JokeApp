package com.JokeApp.Project.service;

import com.JokeApp.Project.model.UserJoke;
import com.JokeApp.Project.repository.UserJokesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserJokesService {
    private final UserJokesRepository userJokesRepository;

    public UserJokesService(UserJokesRepository userJokesRepository){
        this.userJokesRepository = userJokesRepository;
    }

    public List<UserJoke> getUserJokesByUserId(Long userId){
        return userJokesRepository.findAllUserJokes(userId);
    }
}
