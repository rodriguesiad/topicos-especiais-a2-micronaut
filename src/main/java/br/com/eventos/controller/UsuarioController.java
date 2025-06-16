package br.com.eventos.controller;

import br.com.eventos.dto.EventoResponseDTO;
import br.com.eventos.dto.InscricaoResponseDTO;
import br.com.eventos.dto.UsuarioDTO;
import br.com.eventos.dto.UsuarioResponseDTO;
import br.com.eventos.entity.Evento;
import br.com.eventos.entity.Inscricao;
import br.com.eventos.entity.Usuario;
import br.com.eventos.service.evento.EventoService;
import br.com.eventos.service.inscricao.InscricaoService;
import br.com.eventos.service.usuario.UsuarioService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final EventoService eventoService;
    private final InscricaoService inscricaoService;

    public UsuarioController(UsuarioService usuarioService, EventoService eventoService, InscricaoService inscricaoService) {
        this.usuarioService = usuarioService;
        this.eventoService = eventoService;
        this.inscricaoService = inscricaoService;
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<UsuarioResponseDTO> adicionarUsuario(@Body @Valid UsuarioDTO usuario) {
        Usuario novoUsuario = usuarioService.cadastrar(usuario);
        return HttpResponse.created(UsuarioResponseDTO.toResponse(novoUsuario));
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get(value = "/eventos", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Iterable<EventoResponseDTO>> listarTodosEventosPorUsuario() {
        Iterable<Evento> eventos = eventoService.buscarEventosUsuarioLogado();
        List<EventoResponseDTO> response = StreamSupport.stream(eventos.spliterator(), false)
                .map(EventoResponseDTO::toResponse)
                .collect(Collectors.toList());

        return HttpResponse.ok(response);
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get(value = "/inscricoes", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Iterable<InscricaoResponseDTO>> listarTodosInscricaosPorUsuario() {
        Iterable<Inscricao> inscricoes = inscricaoService.buscarInscricoesUsuarioLogado();
        List<InscricaoResponseDTO> response = StreamSupport.stream(inscricoes.spliterator(), false)
                .map(InscricaoResponseDTO::toResponse)
                .collect(Collectors.toList());
        return HttpResponse.ok(response);
    }

}
