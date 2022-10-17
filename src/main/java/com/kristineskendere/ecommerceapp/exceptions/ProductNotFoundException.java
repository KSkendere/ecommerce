package com.kristineskendere.ecommerceapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends NoSuchFieldException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
