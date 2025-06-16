package br.com.eventos.service.evento;

import br.com.eventos.dto.EventoDTO;
import br.com.eventos.exception.ApiException;
import br.com.eventos.model.Evento;

import java.util.Optional;

public interface EventoService {

    Evento cadastrar(EventoDTO dto) throws ApiException;

    Optional<Evento> buscarPorId(Long id) throws ApiException;

    Iterable<Evento> buscarTodos();

    Evento atualizarEvento(Long id, EventoDTO dto) throws ApiException;

    void cancelarEvento(Long id) throws ApiException;

    void concluirEvento(Long id) throws ApiException;

    Iterable<Evento> buscarEventosPorUsuario(Long idUsuario) throws ApiException;

}
