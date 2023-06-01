package com.JokeApp.Project.service;

import com.JokeApp.Project.model.ExternalJoke;
import com.JokeApp.Project.model.ExternalJokeDouble;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConnectWithExternalApiService {
    public String jokeApiUrl = "https://v2.jokeapi.dev/joke/Any";

    public String setup;
    public String delivery;
    public String joke;

    public ConnectWithExternalApiService(String jokeApiUrl) throws IOException, InterruptedException {
        this.jokeApiUrl = jokeApiUrl;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(jokeApiUrl))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();

        if(response.body().contains("setup")){
            ExternalJokeDouble joke = mapper.readValue(response.body(), ExternalJokeDouble.class);
            this.setup = joke.getSetup();
            this.delivery = joke.getDelivery();
        }
        else{
            ExternalJoke externalJoke = mapper.readValue(response.body(), ExternalJoke.class);
            this.joke = externalJoke.getJoke();
        }
    }
    public String getRandomJoke(){
        if(joke==null){
            return setup + "\n" + delivery;
        }
        else{
            return joke;
        }
    }
}
