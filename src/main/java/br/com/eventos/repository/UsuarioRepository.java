package br.com.eventos.repository;

import br.com.eventos.model.Usuario;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {
}
