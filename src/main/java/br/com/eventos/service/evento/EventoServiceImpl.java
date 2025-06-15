package br.com.eventos.service.evento;

import br.com.eventos.model.Evento;
import br.com.eventos.repository.BaseRepository;
import br.com.eventos.repository.UsuarioRepository;
import br.com.eventos.service.BaseServiceImpl;
import jakarta.inject.Singleton;

@Singleton
public class EventoServiceImpl extends BaseServiceImpl<Evento> implements EventoService {

    private final UsuarioRepository usuarioRepository;

    public EventoServiceImpl(BaseRepository<Evento> eventoRepository,
                             UsuarioRepository usuarioRepository) {
        super(eventoRepository);
        this.usuarioRepository = usuarioRepository;
    }


}
