package br.com.eventos.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}

