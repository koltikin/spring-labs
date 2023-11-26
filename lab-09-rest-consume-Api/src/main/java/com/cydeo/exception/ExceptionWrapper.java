package com.cydeo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionWrapper {
    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime localDateTime;

    private Integer errorCount;
    private List<ValidationException> validationExceptions;

    public ExceptionWrapper(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.localDateTime = LocalDateTime.now();
    }
}
