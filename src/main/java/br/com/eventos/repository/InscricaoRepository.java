package br.com.eventos.repository;

import br.com.eventos.model.Inscricao;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;

import java.util.List;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface InscricaoRepository extends BaseRepository<Inscricao> {

    List<Inscricao> findByAtivoAndEventoId(Boolean ativo, Long idEvento);

    List<Inscricao> findByUsuario_Id(Long idUsuario);


}