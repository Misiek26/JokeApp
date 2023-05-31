package com.JokeApp.Project.model;

public class Flag {
    private boolean nsfw;
    private boolean religious;
    private boolean political;
    private boolean racist;
    private boolean sexist;
    private boolean explicit;

    public boolean isNsfw() {
        return nsfw;
    }

    public boolean isReligious() {
        return religious;
    }

    public boolean isPolitical() {
        return political;
    }

    public boolean isRacist() {
        return racist;
    }

    public boolean isSexist() {
        return sexist;
    }

    public boolean isExplicit() {
        return explicit;
    }
}
