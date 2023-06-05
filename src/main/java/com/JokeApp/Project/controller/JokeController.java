package com.JokeApp.Project.controller;

import com.JokeApp.Project.model.Category;
import com.JokeApp.Project.model.Joke;
import com.JokeApp.Project.model.User;
import com.JokeApp.Project.repository.CategoryRepository;
import com.JokeApp.Project.service.CategoryService;
import com.JokeApp.Project.service.JokeService;
import com.JokeApp.Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jokes")
public class JokeController {
    private final JokeService jokeService;
    private final CategoryService categoryService;
    private final UserService userService;

    @Autowired
    public JokeController(JokeService jokeService, CategoryService categoryService, UserService userService) {
        this.jokeService = jokeService;
        this.categoryService = categoryService;
        this.userService = userService;
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

    @GetMapping("/pages")
    public Page<Joke> getAllJokesPageable(Pageable pageable) {
        return jokeService.getAllJokesPageable(pageable);
    }

    @GetMapping("/page")
    public Page<Joke> getJokesPageableById(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return jokeService.getAllJokesPageable(pageable);
    }

    @GetMapping("/category")
    public List<Joke> getJokesByCategoryName(@RequestParam String category){
        return jokeService.getJokesByCategory(category);
    }

    @PostMapping
    public ResponseEntity<Joke> createJoke(@RequestBody Joke joke) {
        Joke createdJoke = jokeService.createJoke(joke);
        if (createdJoke != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdJoke);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/form")
    public RedirectView createJoke(@RequestParam("setup") String setup,
                                   @RequestParam("punchline") String punchline,
                                   @RequestParam("category") String categoryName) {

        int userId = 1;
        Joke joke = new Joke();
        joke.setSetup(setup);
        joke.setPunchline(punchline);

        Optional<Category> category = categoryService.getCategoryByName(categoryName);

        if(category.isPresent()){
            joke.setCategory(category.get());
        }

        Optional<User> user = userService.getUserById((long) userId);
        if(user.isPresent()){
            joke.setUser(user.get());
        }

        Joke createdJoke = jokeService.createJoke(joke);

        if (createdJoke != null) {
            return new RedirectView(("/manage/create-joke?success"));
        }
        return new RedirectView("/error");
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