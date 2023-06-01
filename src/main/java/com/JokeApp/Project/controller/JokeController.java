package com.JokeApp.Project.controller;

import com.JokeApp.Project.model.Joke;
import com.JokeApp.Project.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jokes")
public class JokeController {
    private final JokeService jokeService;

    @Autowired
    public JokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @GetMapping
    public List<Joke> getAllJokes() {
        return jokeService.getAllJokes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Joke> getJokeById(@PathVariable Long id) {
        Optional<Joke> optionalJoke = jokeService.getJokeById(id);
        return optionalJoke.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Joke> createJoke(@RequestBody Joke joke) {
        Joke createdJoke = jokeService.createJoke(joke);
        if (createdJoke != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdJoke);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Joke> updateJoke(@PathVariable Long id, @RequestBody Joke updatedJoke) {
        Joke joke = jokeService.updateJoke(id, updatedJoke);
        if (joke != null) {
            return ResponseEntity.ok(joke);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJoke(@PathVariable Long id) {
        jokeService.deleteJoke(id);
        return ResponseEntity.noContent().build();
    }
}