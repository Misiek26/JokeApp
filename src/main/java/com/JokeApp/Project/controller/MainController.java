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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class MainController {
    @GetMapping("/")
    public String main(Model model) throws JsonProcessingException {
        model.addAttribute("userJokes", getUserJokes(1));
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
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

        ResponseEntity<Category[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/categories/sorted", Category[].class);

        List<Category> categories = List.of(responseEntity.getBody());
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/categories/{categoryName}")
    public String getJokesByCategory(@PathVariable String categoryName, Model model){
        RestTemplate restTemplate = new RestTemplate();

        Joke[] jokeArray = restTemplate.getForObject("http://localhost:8080/api/jokes/category?category=" + categoryName, Joke[].class);
        List<Joke> jokes = Arrays.asList(jokeArray);
        Collections.shuffle(jokes);
        model.addAttribute("jokes", jokes);
        return "category-jokes";
    }

    @GetMapping("/explore")
    public String getAllJokes(Model model){
        RestTemplate restTemplate = new RestTemplate();

        Joke[] jokesArray = restTemplate.getForObject("http://localhost:8080/api/jokes", Joke[].class);
        List<Joke> jokes = Arrays.asList(jokesArray);
        Collections.shuffle(jokes);
        model.addAttribute("jokes", jokes);
        return "explore";
    }

    @GetMapping("/manage")
    public String getManage(){
        return "manage";
    }
}
