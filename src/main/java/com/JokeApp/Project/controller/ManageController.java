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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/manage")
public class ManageController {

    @GetMapping("/create-joke")
    public String addJoke(Model model){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Category[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/categories/sorted", Category[].class);

        List<Category> categories = List.of(responseEntity.getBody());
        model.addAttribute("categories", categories);

        return "manage/create-joke";
    }
}
