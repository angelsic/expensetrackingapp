package com.project.expensetrackingapp.exception.finance;

import com.project.expensetrackingapp.repository.entity.ErrResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Handler Exception for Finance Controller
 * @author Angel Sic
 */
@RestControllerAdvice
public class FinanceHandlerException {
    @ExceptionHandler(AmountNotFound.class)
    public ResponseEntity<ErrResponse> handleAmountNotFound(AmountNotFound ex){
        ErrResponse err = new ErrResponse("03", ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FinanceAlreadyExist.class)
    public ResponseEntity<ErrResponse> handleFinanceAlreadyExist(FinanceAlreadyExist ex){
        ErrResponse err = new ErrResponse("03", ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FinanceNotExist.class)
    public ResponseEntity<ErrResponse> handleFinanceNotExist(FinanceNotExist ex){
        ErrResponse err = new ErrResponse("03", ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
