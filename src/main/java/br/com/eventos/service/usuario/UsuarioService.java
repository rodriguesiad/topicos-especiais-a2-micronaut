package br.com.eventos.service.usuario;

import br.com.eventos.dto.UsuarioDTO;
import br.com.eventos.exception.ApiException;
import br.com.eventos.entity.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Usuario cadastrar(UsuarioDTO model) throws ApiException;

    Optional<Usuario> buscarPorId(Long id) throws ApiException;

    Iterable<Usuario> buscarTodos();

    Usuario buscarPorEmail(String email);
}
