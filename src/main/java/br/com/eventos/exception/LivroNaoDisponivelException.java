package br.com.eventos.exception;

public class LivroNaoDisponivelException extends RuntimeException {
    public LivroNaoDisponivelException(String message) {
        super(message);
    }
}
