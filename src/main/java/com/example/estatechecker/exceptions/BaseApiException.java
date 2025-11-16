package com.example.estatechecker.exceptions;

import org.springframework.http.ProblemDetail;

import java.util.Objects;

public class BaseApiException extends RuntimeException {
    private static final String NULL_PROBLEM_DETAIL_MESSAGE = "Problemdetail must not be null";

    protected final ProblemDetail problemDetail;

    protected BaseApiException(String message, ProblemDetail problemDetail) {
        super(message);
        this.problemDetail = Objects.requireNonNull(problemDetail, NULL_PROBLEM_DETAIL_MESSAGE);
    }

    protected BaseApiException(String message, Throwable cause, ProblemDetail problemDetail) {
        super(message, cause);
        this.problemDetail = Objects.requireNonNull(problemDetail, NULL_PROBLEM_DETAIL_MESSAGE);

    }
}
