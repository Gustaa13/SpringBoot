package com.gustaa13.crud_api.service.exceptions;

public class DatabaseIntegrityException extends RuntimeException {

    public DatabaseIntegrityException(String msg) {
        super(msg);
    }
    
}
