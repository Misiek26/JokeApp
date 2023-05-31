package com.JokeApp.Project.service;

import com.JokeApp.Project.model.Joke;
import com.JokeApp.Project.repository.RandomJokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class RandomJokeService {
    private final RandomJokeRepository randomJokeRepository;

    @Autowired
    public RandomJokeService(RandomJokeRepository randomJokeRepository){
        this.randomJokeRepository = randomJokeRepository;
    }

}
