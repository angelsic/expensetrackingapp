package com.project.expensetrackingapp.exception.category;

import com.project.expensetrackingapp.repository.entity.ErrResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CategoryHandlerException {

    @ExceptionHandler(CategoryNotFound.class)
    public ResponseEntity<ErrResponse> handleCategoryNotFound(CategoryNotFound ex){
        ErrResponse err = new ErrResponse("02", ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryAlreadyExist.class)
    public ResponseEntity<ErrResponse> handleCategoryAlreadyExist(CategoryAlreadyExist ex){
        ErrResponse err = new ErrResponse("02", ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryNotExist.class)
    public ResponseEntity<ErrResponse> handleCategoryNotExist(CategoryNotExist ex){
        ErrResponse err = new ErrResponse("02", ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

}
