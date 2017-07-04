package com.rhb.ginkgo.api;

public class Greeting {

    private final long id;
    private final String letter;

    public Greeting(long id, String letter) {
        this.id = id;
        this.letter = letter;
    }

    public long getId() {
        return id;
    }

    public String getLetter() {
        return letter;
    }
}