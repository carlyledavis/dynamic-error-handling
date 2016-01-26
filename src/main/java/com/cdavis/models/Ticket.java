package com.cdavis.models;

public class Ticket {
    private final String name;
    private final String director;

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }

    public Ticket(String name, String director) {

        this.name = name;
        this.director = director;
    }
}
