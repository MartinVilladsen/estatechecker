package com.example.estatechecker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class ZipCodeNotFoundException extends BaseApiException {

    public ZipCodeNotFoundException(String message) {
        super(message, createProblemDetail(message));
        createProblemDetail(message);
    }

    private static ProblemDetail createProblemDetail(String message) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, message);
        problemDetail.setTitle("Zipcode not found");
        problemDetail.setInstance(URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));

        return problemDetail;
    }
}
