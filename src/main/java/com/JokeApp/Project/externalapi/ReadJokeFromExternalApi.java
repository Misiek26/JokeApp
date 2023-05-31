package com.JokeApp.Project.externalapi;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Controller
public class ReadJokeFromExternalApi {

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/external")
    public String getExternalPage(){
        return "external";
    }

    @GetMapping("/api/external/joke/Any")
    public String getRandomJokeAny(Model model) throws IOException, InterruptedException {
        String url = null;
        if(request.getQueryString()!=null)
            url = request.getQueryString();
        ConnectWithJokeApi connect = new ConnectWithJokeApi("https://v2.jokeapi.dev/joke/Any?" + url);
        String joke = connect.getRandomJoke();
        model.addAttribute("jokeText", joke);
        return "externalJoke";
    }

    @GetMapping("/api/external/joke")
    public String getRandomJoke(Model model) throws IOException, InterruptedException {
        String url = "/Any";
        if(request.getQueryString()!=null)
            url = request.getQueryString();
        ConnectWithJokeApi connect = new ConnectWithJokeApi("https://v2.jokeapi.dev/joke?" + url);
        String joke = connect.getRandomJoke();
        model.addAttribute("jokeText", joke);
        return "externalJoke";
    }

    @GetMapping(value = {"/api/external/joke/Programming", "/api/external/joke/Miscellaneous", "/api/external/joke/Dark"
            ,"/api/external/joke/Pun", "/api/external/joke/Spooky","/api/external/joke/Christmas",})
    public String getRandomJokeCustom(Model model) throws IOException, InterruptedException {
        String url = request.getRequestURL().toString();
        url = url.replace("http://localhost:8080/api/external/joke", "https://v2.jokeapi.dev/joke");
        if(request.getQueryString()!=null)
            url += "?" + request.getQueryString();
        ConnectWithJokeApi connect = new ConnectWithJokeApi(url);
        String joke = connect.getRandomJoke();
        model.addAttribute("jokeText", joke);
        return "externalJoke";
    }
}
