package com.kristineskendere.ecommerceapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends NoSuchFieldException {
    public RecordNotFoundException(String message) {
        super(message);
    }
}
