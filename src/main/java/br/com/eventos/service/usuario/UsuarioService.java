package br.com.eventos.service.usuario;

import br.com.eventos.exception.ApiException;
import br.com.eventos.model.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Usuario cadastrar(Usuario model) throws ApiException;

    Optional<Usuario> buscarPorId(Long id) throws ApiException;

    Iterable<Usuario> buscarTodos();
}
