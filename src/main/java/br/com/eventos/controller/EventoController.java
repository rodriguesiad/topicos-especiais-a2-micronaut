package br.com.eventos.controller;

import br.com.eventos.dto.EventoDTO;
import br.com.eventos.model.Evento;
import br.com.eventos.service.evento.EventoService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import jakarta.validation.Valid;

import java.util.Optional;

@Controller("/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Evento> adicionarEvento(@Body @Valid EventoDTO evento) {
        Evento novoEvento = eventoService.cadastrar(evento);
        return HttpResponse.created(novoEvento);
    }

    @Put(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Evento> atualizarEvento(@PathVariable Long id, @Body @Valid EventoDTO eventoDTO) {
        Evento eventoAtualizado = eventoService.atualizarEvento(id, eventoDTO);
        return HttpResponse.ok(eventoAtualizado);
    }

    @Put(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Void> cancelarEvento(@PathVariable Long id) {
        eventoService.cancelarEvento(id);
        return HttpResponse.noContent();
    }

    @Put(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Void> concluirEvento(@PathVariable Long id) {
        eventoService.concluirEvento(id);
        return HttpResponse.noContent();
    }

    @Get(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Evento> buscarEventoPorId(@PathVariable Long id) {
        Optional<Evento> EventoOpt = eventoService.buscarPorId(id);
        return EventoOpt.map(HttpResponse::ok)
                .orElseGet(HttpResponse::notFound);
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Iterable<Evento>> listarTodosEventos() {
        Iterable<Evento> eventos = eventoService.buscarTodos();
        return HttpResponse.ok(eventos);
    }

    @Get(value = "/{idUsuario}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Iterable<Evento>> listarTodosEventosPorUsuario(@PathVariable Long idUsuario) {
        Iterable<Evento> eventos = eventoService.buscarEventosPorUsuario(idUsuario);
        return HttpResponse.ok(eventos);
    }

}
