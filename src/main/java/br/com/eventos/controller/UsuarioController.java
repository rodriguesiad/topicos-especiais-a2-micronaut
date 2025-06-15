package br.com.eventos.controller;

import br.com.eventos.model.Usuario;
import br.com.eventos.service.usuario.UsuarioService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import jakarta.validation.Valid;

import java.util.Optional;

@Controller("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Usuario> adicionarUsuario(@Body @Valid Usuario usuario) {
        Usuario novoUsuario = usuarioService.cadastrar(usuario);
        return HttpResponse.created(novoUsuario);
    }

    @Get(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> UsuarioOpt = usuarioService.buscarPorId(id);
        return UsuarioOpt.map(HttpResponse::ok)
                .orElseGet(HttpResponse::notFound);
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Iterable<Usuario>> listarTodosUsuarios() {
        Iterable<Usuario> usuarios = usuarioService.buscarTodos();
        return HttpResponse.ok(usuarios);
    }

}
