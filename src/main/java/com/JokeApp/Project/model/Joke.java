package com.JokeApp.Project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "jokes")
public class Joke {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String setup;
    private String punchline;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Joke() {
        // Default constructor required by JPA
    }

    public Joke(String setup, String punchline, Category category) {
        this.setup = setup;
        this.punchline = punchline;
        this.category = category;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}