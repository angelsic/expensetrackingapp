package com.project.expensetrackingapp.exception.user;

import com.project.expensetrackingapp.repository.entity.ErrResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Handler Exception for User Controller
 * @author Angel Sic
 */
@RestControllerAdvice
public class UserHandlerException {
    @ExceptionHandler(UsernameNotFound.class)
    public ResponseEntity<ErrResponse> handleUsernameNotFound(UsernameNotFound ex){
        ErrResponse err = new ErrResponse("01", ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordNotFound.class)
    public ResponseEntity<ErrResponse> handlePasswordNotFound(PasswordNotFound ex){
        ErrResponse err = new ErrResponse("01", ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<ErrResponse> handleUserAlreadyExist(UserAlreadyExist ex){
        ErrResponse err = new ErrResponse("01", ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotExist.class)
    public ResponseEntity<ErrResponse> handleUserNotExist(UserNotExist ex){
        ErrResponse err = new ErrResponse("01", ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
