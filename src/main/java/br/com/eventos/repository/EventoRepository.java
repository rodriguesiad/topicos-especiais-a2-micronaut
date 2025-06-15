package br.com.eventos.repository;

import br.com.eventos.model.Evento;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.util.List;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface EventoRepository extends BaseRepository<Evento> {

    List<Evento> findByUsuario_Id(Long idUsuario);
    
}