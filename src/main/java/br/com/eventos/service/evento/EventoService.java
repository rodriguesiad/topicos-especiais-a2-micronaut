package br.com.eventos.service.evento;

import br.com.eventos.dto.EventoDTO;
import br.com.eventos.exception.ApiException;
import br.com.eventos.model.Evento;
import br.com.eventos.service.BaseService;

public interface EventoService extends BaseService<Evento, Long> {

    Evento cadastrar(EventoDTO dto) throws ApiException;

    Evento atualizarEvento(Long id, EventoDTO dto) throws ApiException;

    void cancelarEvento(Long id) throws ApiException;

    void concluirEvento(Long id) throws ApiException;

    Iterable<Evento> buscarEventosPorUsuario(Long idUsuario) throws ApiException;

}
