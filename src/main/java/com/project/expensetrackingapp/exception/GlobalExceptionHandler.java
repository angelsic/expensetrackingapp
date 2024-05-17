package com.project.expensetrackingapp.exception;

import com.project.expensetrackingapp.exception.auth.InvalidPasswordException;
import com.project.expensetrackingapp.repository.entity.ErrResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrResponse> handleJwtException(JwtException ex, HttpServletResponse response) {
        ErrResponse err = new ErrResponse("06", ex.getMessage());
        response.setContentType("application/json");
        return new ResponseEntity<>(err, ex.getStatus());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrResponse> invalidPasswordException(InvalidPasswordException ex){
        ErrResponse err = new ErrResponse("06", ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
    }

}
