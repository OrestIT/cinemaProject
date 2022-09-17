package com.example.cinema.services.exception;

public class CinemaRuntimeException extends RuntimeException {
    protected CinemaRuntimeException(String msg) {
        super(msg);
    }
}