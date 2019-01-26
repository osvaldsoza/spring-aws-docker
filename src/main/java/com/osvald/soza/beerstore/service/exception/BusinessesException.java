package com.osvald.soza.beerstore.service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class BusinessesException extends RuntimeException {

    private final String code;
    private final HttpStatus status;
}
