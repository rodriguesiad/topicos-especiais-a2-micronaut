package br.com.eventos.service;

import br.com.eventos.exception.ApiException;

import java.io.Serializable;
import java.util.Optional;

public interface BaseService<T, ID extends Serializable> {
    T cadastrar(T model) throws ApiException;

    Optional<T> buscarPorId(ID id) throws ApiException;

    Iterable<T> buscarTodos();
}
