package br.com.eventos.service.usuario;

import br.com.eventos.exception.ApiException;
import br.com.eventos.model.Usuario;
import br.com.eventos.repository.UsuarioRepository;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public Usuario cadastrar(Usuario model) throws ApiException {
        return this.usuarioRepository.save(model);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorId(Long id) throws ApiException {
        return Optional.ofNullable(this.usuarioRepository.findById(id)
                .orElseThrow(() -> new ApiException("Usuário não encontrado: " + id)));
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Usuario> buscarTodos() {
        return this.usuarioRepository.findAll();
    }

}