package com.example.cinema.services.exception.order;

import com.example.cinema.services.exception.CinemaRuntimeException;

public class OrderNotFoundFoundException extends CinemaRuntimeException {
    public OrderNotFoundFoundException(String msg) {
        super(msg);
    }
}
