package br.com.eventos.service.usuario;

import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;
import java.util.Optional;

import br.com.eventos.model.Usuario;
import br.com.eventos.repository.UsuarioRepository;

@Singleton
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }
}