package com.example.cinema.presentation.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Rating {
    ONE("one"), TWO("two"), THREE("three"), FOUR("four"), FIVE("five");

    private final String rating;

    Rating(String rating) {
        this.rating = rating;
    }

    @JsonValue
    public String getRating() {
        return this.rating;
    }
}