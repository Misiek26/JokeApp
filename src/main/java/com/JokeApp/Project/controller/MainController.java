package com.JokeApp.Project.controller;

import com.JokeApp.Project.model.UserJoke;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
