package br.com.eventos.service;

import br.com.eventos.exception.ApiException;

import java.util.Optional;

public interface BaseService<T> {
    T cadastrar(T model) throws ApiException;

    Optional<T> buscarPorId(Long id) throws ApiException;

    Iterable<T> buscarTodos();
}
