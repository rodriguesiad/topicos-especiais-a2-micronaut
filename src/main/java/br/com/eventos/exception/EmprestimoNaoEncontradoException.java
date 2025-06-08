package br.com.eventos.exception;

public class EmprestimoNaoEncontradoException extends RuntimeException {
    public EmprestimoNaoEncontradoException(String message) {
        super(message);
    }
}
