package dev.jgyekye.caseworkers_task_backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice // This annotation makes it a global handler for all controllers
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // --- Example 1: Handling a custom exception (e.g., if a resource is not found) ---
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "") // Clean up URI
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    // --- Example 2: Handling general unexpected exceptions ---
    @ExceptionHandler(Exception.class) // Catch all other unhandled exceptions
    public ResponseEntity<ExceptionResponse> handleGlobalException(
            Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "An unexpected error occurred: " + ex.getMessage(), // More generic message for unexpected errors
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // --- You can add more @ExceptionHandler methods for other specific exceptions ---
    // @ExceptionHandler(MethodArgumentNotValidException.class) for validation errors
    // @ExceptionHandler(HttpMessageNotReadableException.class) for malformed JSON requests
}