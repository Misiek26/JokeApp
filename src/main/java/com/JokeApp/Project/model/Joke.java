package com.JokeApp.Project.model;

public class Joke {
    private boolean error;
    private String category;
    private String type;
    private String joke;
    private Flag flags;
    private int id;
    private boolean safe;
    private String lang;

    public boolean isError() {
        return error;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getJoke() {
        return joke;
    }

    public Flag getFlags() {
        return flags;
    }

    public int getId() {
        return id;
    }

    public boolean isSafe() {
        return safe;
    }

    public String getLang() {
        return lang;
    }
}
