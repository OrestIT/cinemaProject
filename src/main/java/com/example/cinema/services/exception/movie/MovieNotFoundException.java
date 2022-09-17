package com.example.cinema.services.exception.movie;

import com.example.cinema.services.exception.CinemaRuntimeException;

public class MovieNotFoundException extends CinemaRuntimeException {
    public MovieNotFoundException(String msg) {
        super(msg);
    }
}