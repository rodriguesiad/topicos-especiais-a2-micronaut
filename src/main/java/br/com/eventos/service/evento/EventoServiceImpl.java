package br.com.eventos.service.evento;

import br.com.eventos.dto.EventoDTO;
import br.com.eventos.exception.ApiException;
import br.com.eventos.model.Evento;
import br.com.eventos.model.Usuario;
import br.com.eventos.repository.BaseRepository;
import br.com.eventos.repository.EventoRepository;
import br.com.eventos.repository.InscricaoRepository;
import br.com.eventos.repository.UsuarioRepository;
import br.com.eventos.service.BaseServiceImpl;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

@Singleton
public class EventoServiceImpl extends BaseServiceImpl<Evento> implements EventoService {

    private final UsuarioRepository usuarioRepository;
    private final InscricaoRepository inscricaoRepository;


    public EventoServiceImpl(BaseRepository<Evento> eventoRepository,
                             UsuarioRepository usuarioRepository,
                             InscricaoRepository inscricaoRepository) {
        super(eventoRepository);
        this.usuarioRepository = usuarioRepository;
        this.inscricaoRepository = inscricaoRepository;
    }

    @Override
    @Transactional
    public Evento cadastrarEvento(EventoDTO dto) throws ApiException {
        Usuario usuario = buscarUsuarioLogado();
        Evento evento = new Evento(null, dto.nome(), dto.data(), dto.local(), usuario, true);
        return super.cadastrar(evento);
    }

    @Override
    @Transactional
    public Evento atualizarEvento(Long id, EventoDTO dto) throws ApiException {
        Evento evento = getRepository().findById(id).orElseThrow(() -> new ApiException("Evento não encontrado"));
        Usuario usuarioLogado = buscarUsuarioLogado();

        if (!evento.usuario().id().equals(usuarioLogado.id())) {
            throw new IllegalArgumentException("Você não tem permissão para atualizar este evento.");
        }

        evento = new Evento(id, dto.nome(), dto.data(), dto.local(), evento.usuario(), evento.ativo());
        return getRepository().update(evento);
    }

    private Usuario buscarUsuarioLogado() {
        return usuarioRepository.findById(1L).orElseThrow(() -> new ApiException("Usuário não encontrado"));
    }

    @Override
    public EventoRepository getRepository() {
        return (EventoRepository) super.getRepository();
    }

}
