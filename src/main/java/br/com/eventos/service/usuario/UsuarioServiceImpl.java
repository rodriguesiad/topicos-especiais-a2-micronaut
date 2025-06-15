package br.com.eventos.service.usuario;

import br.com.eventos.model.Usuario;
import br.com.eventos.repository.UsuarioRepository;
import br.com.eventos.service.BaseServiceImpl;
import jakarta.inject.Singleton;

@Singleton
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService {

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        super(usuarioRepository);
    }

    @Override
    public UsuarioRepository getRepository() {
        return (UsuarioRepository) super.getRepository();
    }

}