package br.com.eventos.service.inscricao;

import br.com.eventos.dto.InscricaoDTO;
import br.com.eventos.exception.ApiException;
import br.com.eventos.model.Inscricao;
import br.com.eventos.service.BaseService;

public interface InscricaoService extends BaseService<Inscricao> {

    Inscricao cadastrarInscricao(InscricaoDTO dto) throws ApiException;

    void cancelarInscricao(Long idInscricao) throws ApiException;

    Iterable<Inscricao> buscarInscricoesPorEvento(Long eventoId);

    Iterable<Inscricao> buscarInscricaoUsuario(Long idUsuario) throws ApiException;

}
