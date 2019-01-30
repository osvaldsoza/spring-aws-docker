package com.osvald.soza.beerstore.service.exception;

import org.springframework.http.HttpStatus;

public class BeerNotExistException extends BusinessesException {


    public BeerNotExistException() {
        super("beer-6", HttpStatus.NOT_FOUND);
    }
}
