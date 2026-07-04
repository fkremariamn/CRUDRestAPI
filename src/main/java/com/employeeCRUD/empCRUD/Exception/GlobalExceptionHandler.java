package com.employeeCRUD.empCRUD.Exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ErrorResponse buildError(HttpStatus status,
                                     String message,
                                     HttpServletRequest request) {
        return new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI()
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {
        return new ResponseEntity<>(
                buildError(HttpStatus.NOT_FOUND,
                        ex.getMessage(),
                        request),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(
            BadRequestException ex,
            HttpServletRequest request) {
        return new ResponseEntity<>(
                buildError(HttpStatus.BAD_REQUEST,
                        ex.getMessage(),
                        request),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(
            UnauthorizedException ex,
            HttpServletRequest request) {
        return new ResponseEntity<>(
                buildError(HttpStatus.UNAUTHORIZED,
                        ex.getMessage(),
                        request),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbidden(
            ForbiddenException ex,
            HttpServletRequest request) {
        return new ResponseEntity<>(
                buildError(HttpStatus.FORBIDDEN,
                        ex.getMessage(),
                        request),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(
            AccessDeniedException ex,
            HttpServletRequest request) {
        return new ResponseEntity<>(
                buildError(HttpStatus.FORBIDDEN,
                        "Access Denied: " + ex.getMessage(),
                        request),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(
            BadCredentialsException ex,
            HttpServletRequest request) {
        return new ResponseEntity<>(
                buildError(HttpStatus.UNAUTHORIZED,
                        "Invalid username or password",
                        request),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleCustomAuthentication(
            AuthenticationException ex,
            HttpServletRequest request) {
        return new ResponseEntity<>(
                buildError(HttpStatus.UNAUTHORIZED,
                        ex.getMessage(),
                        request),
                HttpStatus.UNAUTHORIZED);
    }

    // JWT Exception Handlers
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handleExpiredJwt(
            ExpiredJwtException ex,
            HttpServletRequest request) {
        return new ResponseEntity<>(
                buildError(HttpStatus.UNAUTHORIZED,
                        "JWT token has expired. Please login again.",
                        request),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ErrorResponse> handleMalformedJwt(
            MalformedJwtException ex,
            HttpServletRequest request) {
        return new ResponseEntity<>(
                buildError(HttpStatus.UNAUTHORIZED,
                        "Invalid JWT token format.",
                        request),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorResponse> handleSignatureException(
            SignatureException ex,
            HttpServletRequest request) {
        return new ResponseEntity<>(
                buildError(HttpStatus.UNAUTHORIZED,
                        "Invalid JWT signature.",
                        request),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex,
            HttpServletRequest request) {
        return new ResponseEntity<>(
                buildError(HttpStatus.BAD_REQUEST,
                        ex.getMessage(),
                        request),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        String message = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();
        return new ResponseEntity<>(
                buildError(HttpStatus.BAD_REQUEST,
                        message != null ? message : "Validation failed",
                        request),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(
            Exception ex,
            HttpServletRequest request) {
        return new ResponseEntity<>(
                buildError(HttpStatus.INTERNAL_SERVER_ERROR,
                        "An unexpected error occurred: " + ex.getMessage(),
                        request),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}