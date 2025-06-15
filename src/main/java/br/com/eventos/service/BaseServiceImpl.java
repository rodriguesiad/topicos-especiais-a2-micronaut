package br.com.eventos.service;

import br.com.eventos.exception.ApiException;
import br.com.eventos.repository.BaseRepository;
import io.micronaut.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;

public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    private final BaseRepository<T, ID> repository;

    protected BaseServiceImpl(BaseRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public T cadastrar(T model) throws ApiException {
        return this.repository.save(model);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> buscarPorId(ID id) throws ApiException {
        return Optional.ofNullable(this.repository.findById(id)
                .orElseThrow(() -> new ApiException("Registro não encontrado: " + id)));
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<T> buscarTodos() {
        return this.repository.findAll();
    }

    public BaseRepository<T, ID> getRepository() {
        return this.repository;
    }
}
