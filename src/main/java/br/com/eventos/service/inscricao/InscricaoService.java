package br.com.eventos.service.inscricao;

import br.com.eventos.dto.InscricaoDTO;
import br.com.eventos.exception.ApiException;
import br.com.eventos.entity.Inscricao;

import java.util.Optional;

public interface InscricaoService {

    Inscricao cadastrar(InscricaoDTO dto) throws ApiException;

    Optional<Inscricao> buscarPorId(Long id) throws ApiException;

    Iterable<Inscricao> buscarTodos();

    void cancelarInscricao(Long idInscricao) throws ApiException;

    Iterable<Inscricao> buscarInscricoesPorEvento(Long eventoId);

    Iterable<Inscricao> buscarInscricoesUsuarioLogado() throws ApiException;

}
