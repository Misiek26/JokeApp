package com.JokeApp.Project.controller;

import com.JokeApp.Project.model.Category;
import com.JokeApp.Project.model.Joke;
import com.JokeApp.Project.model.UserJoke;
import com.JokeApp.Project.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(Model model) throws JsonProcessingException {
        model.addAttribute("userJokes", getUserJokes(1));
        return "index";
    }

    public List<UserJoke> getUserJokes(Integer userId) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/user-jokes/" + userId, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<UserJoke> userJokes = objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<UserJoke>>() {});
        return userJokes;
    }

    @GetMapping("/categories")
    public String getCategories(Model model){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Category[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/categories", Category[].class);

        List<Category> categories = List.of(responseEntity.getBody());
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/categories/{categoryName}")
    public String getJokesByCategory(@PathVariable String categoryName, Model model){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Joke[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/jokes/category?category=" + categoryName, Joke[].class);

        List<Joke> jokes = List.of(responseEntity.getBody());
        model.addAttribute("jokes", jokes);
        System.out.println(jokes.get(0).getCategory().getName());
        return "category-jokes";
    }
}
