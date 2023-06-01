package com.JokeApp.Project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserJoke {
    @Id
    private Long id;
    private String category;
    private String setup;
    private String punchline;
    private String created;

    public UserJoke() {
    }

    public UserJoke(Long id, String category, String setup, String punchline, String created) {
        this.id = id;
        this.category = category;
        this.setup = setup;
        this.punchline = punchline;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getPunchline() {
        return punchline;
    }

    public void setPunchline(String punchline) {
        this.punchline = punchline;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String date) {
        this.created = created;
    }
}
