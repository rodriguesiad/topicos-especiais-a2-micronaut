package br.com.eventos.repository;

import br.com.eventos.model.Inscricao;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface InscricaoRepository extends CrudRepository<Inscricao, Long> {
}