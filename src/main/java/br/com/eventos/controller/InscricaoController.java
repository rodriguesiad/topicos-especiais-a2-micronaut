package br.com.eventos.controller;

import br.com.eventos.dto.InscricaoDTO;
import br.com.eventos.dto.InscricaoResponseDTO;
import br.com.eventos.entity.Inscricao;
import br.com.eventos.service.inscricao.InscricaoService;
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
@Controller("/inscricoes")
public class InscricaoController {

    private final InscricaoService inscricaoService;

    public InscricaoController(InscricaoService inscricaoService) {
        this.inscricaoService = inscricaoService;
    }

    @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<InscricaoResponseDTO> adicionarInscricao(@Body @Valid InscricaoDTO inscricao) {
        Inscricao novoInscricao = inscricaoService.cadastrar(inscricao);
        return HttpResponse.created(InscricaoResponseDTO.toResponse(novoInscricao));
    }

    @Put(value = "/cancelar/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Void> cancelarInscricao(@PathVariable Long id) {
        inscricaoService.cancelarInscricao(id);
        return HttpResponse.noContent();
    }

    @Get(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<InscricaoResponseDTO> buscarInscricaoPorId(@PathVariable Long id) {
        Optional<Inscricao> inscricaoOpt = inscricaoService.buscarPorId(id);
        return inscricaoOpt
                .map(inscricao -> HttpResponse.ok(InscricaoResponseDTO.toResponse(inscricao)))
                .orElseGet(HttpResponse::notFound);
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Iterable<InscricaoResponseDTO>> listarTodosInscricoes() {
        Iterable<Inscricao> inscricoes = inscricaoService.buscarTodos();
        List<InscricaoResponseDTO> response = StreamSupport.stream(inscricoes.spliterator(), false)
                .map(InscricaoResponseDTO::toResponse)
                .collect(Collectors.toList());
        return HttpResponse.ok(response);
    }

    @Get(value = "/evento/{id}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Iterable<InscricaoResponseDTO>> listarTodosInscricoesPorEvento(@PathVariable Long id) {
        Iterable<Inscricao> inscricoes = inscricaoService.buscarInscricoesPorEvento(id);
        List<InscricaoResponseDTO> response = StreamSupport.stream(inscricoes.spliterator(), false)
                .map(InscricaoResponseDTO::toResponse)
                .collect(Collectors.toList());
        return HttpResponse.ok(response);
    }

}
