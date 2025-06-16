package br.com.eventos.controller;

import br.com.eventos.dto.EventoDTO;
import br.com.eventos.dto.EventoResponseDTO;
import br.com.eventos.entity.Evento;
import br.com.eventos.service.evento.EventoService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<EventoResponseDTO> adicionarEvento(@Body @Valid EventoDTO evento) {
        Evento novoEvento = eventoService.cadastrar(evento);
        return HttpResponse.created(EventoResponseDTO.toResponse(novoEvento));
    }

    @Put(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<EventoResponseDTO> atualizarEvento(@PathVariable Long id, @Body @Valid EventoDTO eventoDTO) {
        Evento eventoAtualizado = eventoService.atualizarEvento(id, eventoDTO);
        return HttpResponse.ok(EventoResponseDTO.toResponse(eventoAtualizado));
    }

    @Put(value = "/cancelar/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Void> cancelarEvento(@PathVariable Long id) {
        eventoService.cancelarEvento(id);
        return HttpResponse.noContent();
    }

    @Put(value = "/concluir/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Void> concluirEvento(@PathVariable Long id) {
        eventoService.concluirEvento(id);
        return HttpResponse.noContent();
    }

    @Get(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<EventoResponseDTO> buscarEventoPorId(@PathVariable Long id) {
        Optional<Evento> eventoOpt = eventoService.buscarPorId(id);
        return eventoOpt
                .map(evento -> HttpResponse.ok(EventoResponseDTO.toResponse(evento)))
                .orElseGet(HttpResponse::notFound);
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Iterable<EventoResponseDTO>> listarTodosEventos() {
        Iterable<Evento> eventos = eventoService.buscarTodos();
        List<EventoResponseDTO> response = StreamSupport.stream(eventos.spliterator(), false)
                .map(EventoResponseDTO::toResponse)
                .collect(Collectors.toList());
        return HttpResponse.ok(response);
    }

}
