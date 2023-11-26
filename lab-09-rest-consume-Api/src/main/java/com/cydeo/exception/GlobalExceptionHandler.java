package com.cydeo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CurrencyInvalidException.class)
    public ResponseEntity<ExceptionWrapper> handleCurrencyInvalidException(CurrencyInvalidException exception) {
        ExceptionWrapper exceptionWrapper = new ExceptionWrapper(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionWrapper);
    }
//    @ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
//    public ResponseEntity<ExceptionWrapper> handleGenericException(){
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(ExceptionWrapper.builder()
//                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//                        .message("Action failed: An error occurred")
//                        .localDateTime(LocalDateTime.now())
//                        .build());
//    }
}
