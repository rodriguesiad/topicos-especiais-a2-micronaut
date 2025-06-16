package br.com.eventos.repository;

import br.com.eventos.model.Evento;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface EventoRepository extends CrudRepository<Evento, Long> {

    List<Evento> findByUsuario_Id(Long idUsuario);

}