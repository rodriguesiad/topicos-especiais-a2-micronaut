package br.com.eventos.controller;

import br.com.eventos.model.Inscricao;
import br.com.eventos.service.inscricao.InscricaoService;
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

@Controller("/inscricoes")
public class InscricaoController {

    private final InscricaoService inscricaoService;

    public InscricaoController(InscricaoService inscricaoService) {
        this.inscricaoService = inscricaoService;
    }

    @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Inscricao> adicionarInscricao(@Body @Valid Inscricao inscricao) {
        Inscricao novoInscricao = inscricaoService.cadastrar(inscricao);
        return HttpResponse.created(novoInscricao);
    }

    @Put(value = "/{id}", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Void> cancelarInscricao(@PathVariable Long id) {
        inscricaoService.cancelarInscricao(id);
        return HttpResponse.noContent();
    }

    @Get(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Inscricao> buscarInscricaoPorId(@PathVariable Long id) {
        Optional<Inscricao> InscricaoOpt = inscricaoService.buscarPorId(id);
        return InscricaoOpt.map(HttpResponse::ok)
                .orElseGet(HttpResponse::notFound);
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Iterable<Inscricao>> listarTodosInscricaos() {
        Iterable<Inscricao> inscricoes = inscricaoService.buscarTodos();
        return HttpResponse.ok(inscricoes);
    }

    @Get(value = "/{idUsuario}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Iterable<Inscricao>> listarTodosInscricaosPorUsuario(@PathVariable Long idUsuario) {
        Iterable<Inscricao> inscricoes = inscricaoService.buscarInscricoesUsuario(idUsuario);
        return HttpResponse.ok(inscricoes);
    }
}
