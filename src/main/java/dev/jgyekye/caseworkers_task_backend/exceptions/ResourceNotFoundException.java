package dev.jgyekye.caseworkers_task_backend.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Optional: You can use @ResponseStatus directly on the exception
// if you only need a simple HTTP status code and no custom JSON response.
// However, @ControllerAdvice is more flexible for custom bodies.
// @ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
