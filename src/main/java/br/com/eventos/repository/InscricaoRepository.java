package br.com.eventos.repository;

import br.com.eventos.entity.Inscricao;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface InscricaoRepository extends CrudRepository<Inscricao, Long> {

    @Join(value = "usuario", type = Join.Type.FETCH)
    @Join(value = "evento", type = Join.Type.FETCH)
    @Join(value = "evento.usuario", type = Join.Type.FETCH)
    List<Inscricao> findByAtivoAndEventoId(Boolean ativo, Long idEvento);

    @Join(value = "usuario", type = Join.Type.FETCH)
    @Join(value = "evento", type = Join.Type.FETCH)
    @Join(value = "evento.usuario", type = Join.Type.FETCH)
    List<Inscricao> findByUsuario_Id(Long idUsuario);

    @Join(value = "usuario", type = Join.Type.FETCH)
    @Join(value = "evento", type = Join.Type.FETCH)
    @Join(value = "evento.usuario", type = Join.Type.FETCH)
    List<Inscricao> findAll();

    @Join(value = "usuario", type = Join.Type.FETCH)
    @Join(value = "evento", type = Join.Type.FETCH)
    @Join(value = "evento.usuario", type = Join.Type.FETCH)
    Optional<Inscricao> findById(Long id);

    Optional<Inscricao> findByEventoIdAndUsuarioIdAndAtivoTrue(Long eventoId, Long usuarioId);

}