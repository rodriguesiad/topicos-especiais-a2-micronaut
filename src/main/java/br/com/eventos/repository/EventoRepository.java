package br.com.eventos.repository;

import br.com.eventos.entity.Evento;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface EventoRepository extends CrudRepository<Evento, Long> {

    @Join(value = "usuario", type = Join.Type.FETCH)
    List<Evento> findByUsuario_Id(Long idUsuario);

    @Join(value = "usuario", type = Join.Type.FETCH)
    List<Evento> findAll();

    @Join(value = "usuario", type = Join.Type.FETCH)
    Optional<Evento> findById(Long id);

}