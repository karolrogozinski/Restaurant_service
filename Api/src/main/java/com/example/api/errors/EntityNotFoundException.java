package com.example.api.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception called when requested entity was not found in the database
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Entity")
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id) {
        super("Could not find: " + id);
    }
}
