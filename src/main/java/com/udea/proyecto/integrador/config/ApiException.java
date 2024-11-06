package com.udea.proyecto.integrador.config;

public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }
}
