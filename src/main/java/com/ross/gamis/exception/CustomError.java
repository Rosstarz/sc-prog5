package com.ross.gamis.exception;

import org.springframework.validation.FieldError;
import java.util.ArrayList;
import java.util.List;

public class CustomError {
    private int status;
    private String message;
    private List<FieldError> fieldErrors = new ArrayList<>();

    public CustomError(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void addFieldError(String path, String message) {
        FieldError error = new FieldError(path, message, message);
        fieldErrors.add(error);
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }
}
