package com.project.expensetrackingapp.exception.portfolio;

import com.project.expensetrackingapp.exception.finance.FinanceNotExist;
import com.project.expensetrackingapp.repository.entity.ErrResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PortfolioHandlerException {
    @ExceptionHandler(PortfolioAlreadyExist.class)
    public ResponseEntity<ErrResponse> handlePortfolioAlreadyExist(PortfolioAlreadyExist ex){
        ErrResponse err = new ErrResponse("04", ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PortfolioNotExist.class)
    public ResponseEntity<ErrResponse> handlePortfolioNotExist(PortfolioNotExist ex){
        ErrResponse err = new ErrResponse("04", ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
