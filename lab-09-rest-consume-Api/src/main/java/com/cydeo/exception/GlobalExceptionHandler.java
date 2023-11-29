package com.cydeo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
    public ResponseEntity<ExceptionWrapper> handleGenericException(Throwable exception){
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionWrapper("Action failed: An error occurred",
                        HttpStatus.INTERNAL_SERVER_ERROR));
    }
    @ExceptionHandler(CurrencyInvalidException.class)
    public ResponseEntity<ExceptionWrapper> handleCurrencyInvalidException(CurrencyInvalidException exception) {
        ExceptionWrapper exceptionWrapper = new ExceptionWrapper(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionWrapper);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ExceptionWrapper> handleOrderNotFoundException(OrderNotFoundException exception) {
        ExceptionWrapper exceptionWrapper = new ExceptionWrapper(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionWrapper);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionWrapper> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
//        exception.printStackTrace();

        ExceptionWrapper exceptionWrapper = new ExceptionWrapper("Invalid Input(s)", HttpStatus.BAD_REQUEST);
        List<ValidationException> exceptions = new ArrayList<>();
        for (FieldError error : exception.getFieldErrors()){
            String fieldName = error.getField();
            Object rejectedValue = error.getRejectedValue();
            String message = error.getDefaultMessage();
            exceptions.add(new ValidationException(fieldName,rejectedValue,message));
        }
        exceptionWrapper.setValidationExceptions(exceptions);
        exceptionWrapper.setErrorCount(exceptions.size());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionWrapper);
    }
}
