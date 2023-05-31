package com.JokeApp.Project.externalapi;

import com.JokeApp.Project.model.Joke;
import com.JokeApp.Project.model.JokeDouble;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConnectWithJokeApi {
    public String jokeApiUrl = "https://v2.jokeapi.dev/joke/Any";

    public String setup;
    public String delivery;
    public String joke;

    public ConnectWithJokeApi(String jokeApiUrl) throws IOException, InterruptedException {
        this.jokeApiUrl = jokeApiUrl;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(jokeApiUrl))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();

        if(response.body().contains("setup")){
            JokeDouble joke = mapper.readValue(response.body(), JokeDouble.class);
            this.setup = joke.getSetup();
            this.delivery = joke.getDelivery();
        }
        else{
            Joke joke = mapper.readValue(response.body(), Joke.class);
            this.joke = joke.getJoke();
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
