package com.ticketflow.bookingservice.exceptions;

import feign.FeignException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Gère toutes les erreurs de Feign (404, 500, etc.)
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Void> handleFeignException(FeignException e) {
        // Si Feign dit que l'erreur est une 404 (Not Found)
        if (e.status() == 404) {
            return ResponseEntity.notFound().build();
        }
        // Sinon, on renvoie une 503
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }

    // Gère le circuit ouvert et les erreurs de connexion
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void> handleUnavailable(Exception e) {
        if (e instanceof CallNotPermittedException || e instanceof java.net.ConnectException) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}