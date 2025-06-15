package br.com.eventos.repository;

import io.micronaut.data.repository.CrudRepository;

import java.io.Serializable;


public interface BaseRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {
}
