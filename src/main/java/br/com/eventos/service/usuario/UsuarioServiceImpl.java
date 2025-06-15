package br.com.eventos.service.usuario;

import br.com.eventos.model.Usuario;
import br.com.eventos.repository.BaseRepository;
import br.com.eventos.service.BaseServiceImpl;
import jakarta.inject.Singleton;

@Singleton
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario> implements UsuarioService {
    public UsuarioServiceImpl(BaseRepository<Usuario> usuarioRepository) {
        super(usuarioRepository);
    }

}