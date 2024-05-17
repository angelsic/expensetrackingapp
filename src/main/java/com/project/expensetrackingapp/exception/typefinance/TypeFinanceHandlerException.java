package com.project.expensetrackingapp.exception.typefinance;

import com.project.expensetrackingapp.repository.entity.ErrResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TypeFinanceHandlerException {

    @ExceptionHandler(TypeFinanceNotFound.class)
    public ResponseEntity<ErrResponse> handleCategoryNotFound(TypeFinanceNotFound ex){
        ErrResponse err = new ErrResponse("05", ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TypeFinanceAlreadyExist.class)
    public ResponseEntity<ErrResponse> handleCategoryAlreadyExist(TypeFinanceAlreadyExist ex){
        ErrResponse err = new ErrResponse("05", ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TypeFinanceNotExist.class)
    public ResponseEntity<ErrResponse> handleCategoryNotExist(TypeFinanceNotExist ex){
        ErrResponse err = new ErrResponse("05", ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

}
