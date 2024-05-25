package com.ross.gamis.exception;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class CustomRestExceptionHandler {
    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<CustomError> resourceNotFoundException(MethodArgumentNotValidException ex, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
        return new ResponseEntity<CustomError>(processFieldErrors(fieldErrors), HttpStatus.BAD_REQUEST);
    }

    private CustomError processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
        CustomError error = new CustomError(HttpStatus.BAD_REQUEST.value(), "validation error");
        for (org.springframework.validation.FieldError fieldError: fieldErrors) {
            error.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return error;
    }
}
