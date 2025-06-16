package br.com.eventos.service.usuario;

import br.com.eventos.dto.UsuarioDTO;
import br.com.eventos.entity.Usuario;
import br.com.eventos.exception.ApiException;
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
    public Usuario cadastrar(UsuarioDTO dto) throws ApiException {
        Optional<Usuario> usuarioCadastrado = this.usuarioRepository.findByEmail(dto.email());

        if (usuarioCadastrado.isPresent()) {
            throw new ApiException("O e-mail informado já está cadastrado.");
        }

        Usuario usuario = new Usuario(null, dto.nome(), dto.email(), dto.senha());

        return this.usuarioRepository.save(usuario);
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

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }

}