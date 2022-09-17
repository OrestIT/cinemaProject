package com.example.cinema.services.exception.movie;

import com.example.cinema.services.exception.CinemaRuntimeException;

public class MovieDeleteException extends CinemaRuntimeException {
    public MovieDeleteException(String msg) {
        super(msg);
    }
}