package com.example.cinema.services.exception.order;

import com.example.cinema.services.exception.CinemaRuntimeException;

public class OrderDeleteException extends CinemaRuntimeException {

    protected OrderDeleteException(String msg) {
        super(msg);
    }
}
