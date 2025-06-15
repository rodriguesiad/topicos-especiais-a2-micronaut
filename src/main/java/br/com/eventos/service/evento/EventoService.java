package br.com.eventos.service.evento;

import br.com.eventos.dto.EventoDTO;
import br.com.eventos.exception.ApiException;
import br.com.eventos.model.Evento;
import br.com.eventos.service.BaseService;

public interface EventoService extends BaseService<Evento> {

    Evento cadastrarEvento(EventoDTO dto) throws ApiException;

    Evento atualizarEvento(Long id, EventoDTO dto) throws ApiException;

}
