package br.com.eventos.service;

import br.com.eventos.exception.ApiException;
import br.com.eventos.repository.BaseRepository;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class BaseServiceImpl<T> implements BaseService<T> {

    private final BaseRepository<T> repository;

    protected BaseServiceImpl(BaseRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public T cadastrar(T model) throws ApiException {
        return this.repository.save(model);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> buscarPorId(Long id) throws ApiException {
        return Optional.ofNullable(this.repository.findById(id)
                .orElseThrow(() -> new ApiException("Registro não encontrado: " + id)));
    }

    @Transactional(readOnly = true)
    public Iterable<T> buscarTodos() {
        return this.repository.findAll();
    }

    public BaseRepository<T> getRepository() {
        return this.repository;
    }
}
