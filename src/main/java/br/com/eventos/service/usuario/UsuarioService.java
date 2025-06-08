package br.com.eventos.service.usuario;

import java.util.Optional;

import br.com.eventos.model.Usuario;

public interface UsuarioService {
    Usuario salvarUsuario(Usuario usuario);

    Optional<Usuario> buscarPorId(Long id);

    Iterable<Usuario> buscarTodos();
}
