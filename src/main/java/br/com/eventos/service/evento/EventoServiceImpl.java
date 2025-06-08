package br.com.eventos.service.evento;

import br.com.eventos.repository.EventoRepository;
import br.com.eventos.repository.UsuarioRepository;
import jakarta.inject.Singleton;

@Singleton
public class EventoServiceImpl implements EventoService {
    private final EventoRepository eventoRepository;
    private final UsuarioRepository usuarioRepository;

    public EventoServiceImpl(EventoRepository eventoRepository,
                             UsuarioRepository usuarioRepository) {
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
    }


}
