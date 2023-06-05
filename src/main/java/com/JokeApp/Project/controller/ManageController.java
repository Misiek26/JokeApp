package com.JokeApp.Project.controller;

import com.JokeApp.Project.model.Category;
import com.JokeApp.Project.model.UserJoke;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/manage")
public class ManageController {

    @GetMapping("/create-joke")
    public String createJoke(Model model){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Category[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/categories/sorted", Category[].class);

        List<Category> categories = List.of(responseEntity.getBody());
        model.addAttribute("categories", categories);

        return "manage/create-joke";
    }

    @GetMapping("/update-joke")
    public String updateJoke(Model model) throws JsonProcessingException {
        int userId = 1;
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/user-jokes/" + userId, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        List<UserJoke> userJokes = objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<UserJoke>>() {});

        model.addAttribute("userJokes", userJokes);

        return "manage/update-joke";
    }
}
