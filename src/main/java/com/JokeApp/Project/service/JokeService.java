package com.JokeApp.Project.service;

import com.JokeApp.Project.model.Joke;
import com.JokeApp.Project.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JokeService {
    private final JokeRepository jokeRepository;

    @Autowired
    public JokeService(JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    public List<Joke> getAllJokes() {
        return jokeRepository.findAll();
    }

    public Optional<Joke> getJokeById(Long id) {
        return jokeRepository.findById(id);
    }

    public Page<Joke> getAllJokesPageable(Pageable pageable){
        return  jokeRepository.findAll(pageable);
    }

    public List<Joke> getJokesByCategory(String categoryName){
        return jokeRepository.findAllByCategory(categoryName);
    }

    public Joke createJoke(Joke joke) {
        LocalDateTime created = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        joke.setCreated(created.format(format));
        return jokeRepository.save(joke);
    }

    public Joke updateJoke(Long id, Joke updatedJoke) {
        Optional<Joke> optionalJoke = jokeRepository.findById(id);
        if (optionalJoke.isPresent()) {
            Joke existingJoke = optionalJoke.get();
            existingJoke.setSetup(updatedJoke.getSetup());
            existingJoke.setPunchline(updatedJoke.getPunchline());
            existingJoke.setCategory(updatedJoke.getCategory());
            return jokeRepository.save(existingJoke);
        }
        return null; // Or throw an exception indicating that the joke was not found
    }

    public void deleteJoke(Long id) {
        if(jokeRepository.existsById(id)){
            jokeRepository.deleteById(id);
        }
        else{
            throw new IllegalArgumentException("Joke with this id is not exists");
        }
    }
}