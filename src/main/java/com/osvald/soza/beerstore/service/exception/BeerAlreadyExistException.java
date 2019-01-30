package com.osvald.soza.beerstore.service.exception;

import org.springframework.http.HttpStatus;

public class BeerAlreadyExistException extends BusinessesException {
    public   BeerAlreadyExistException() {
        super("beer-5", HttpStatus.BAD_REQUEST);
    }
}
