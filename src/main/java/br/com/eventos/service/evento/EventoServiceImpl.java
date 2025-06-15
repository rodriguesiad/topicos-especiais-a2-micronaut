package br.com.eventos.service.evento;

import br.com.eventos.dto.EventoDTO;
import br.com.eventos.exception.ApiException;
import br.com.eventos.model.Evento;
import br.com.eventos.model.Usuario;
import br.com.eventos.model.enums.StatusEvento;
import br.com.eventos.repository.BaseRepository;
import br.com.eventos.repository.EventoRepository;
import br.com.eventos.repository.UsuarioRepository;
import br.com.eventos.service.BaseServiceImpl;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;

@Singleton
public class EventoServiceImpl extends BaseServiceImpl<Evento> implements EventoService {

    private final UsuarioRepository usuarioRepository;

    public EventoServiceImpl(BaseRepository<Evento> eventoRepository,
                             UsuarioRepository usuarioRepository) {
        super(eventoRepository);
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public Evento cadastrar(EventoDTO dto) throws ApiException {
        Usuario usuario = buscarUsuarioLogado();
        Evento evento = new Evento(null, dto.nome(), dto.data(), dto.local(), usuario, StatusEvento.ATIVO);
        return super.cadastrar(evento);
    }

    @Override
    @Transactional
    public Evento atualizarEvento(Long id, EventoDTO dto) throws ApiException {
        Evento evento = getRepository().findById(id).orElseThrow(() -> new ApiException("Evento não encontrado"));

        if (!isUsuarioDono(evento.usuario().id())) {
            throw new IllegalArgumentException("Você não tem permissão para atualizar este evento.");
        }

        evento = new Evento(id, dto.nome(), dto.data(), dto.local(), evento.usuario(), evento.status());
        return getRepository().update(evento);
    }

    @Override
    @Transactional
    public void cancelarEvento(Long id) throws ApiException {
        Evento evento = getRepository().findById(id).orElseThrow(() -> new ApiException("Evento não encontrado"));

        if (!isUsuarioDono(evento.usuario().id())) {
            throw new IllegalArgumentException("Você não tem permissão para cancelar este evento.");
        }

        evento = new Evento(evento.id(), evento.nome(), evento.data(), evento.local(), evento.usuario(), StatusEvento.CANCELADO);
        getRepository().update(evento);
    }

    @Override
    @Transactional
    public void concluirEvento(Long id) throws ApiException {
        Evento evento = getRepository().findById(id).orElseThrow(() -> new ApiException("Evento não encontrado"));

        if (!isUsuarioDono(evento.usuario().id())) {
            throw new IllegalArgumentException("Você não tem permissão para concluir este evento.");
        }

        evento = new Evento(evento.id(), evento.nome(), evento.data(), evento.local(), evento.usuario(), StatusEvento.CONCLUIDO);
        getRepository().update(evento);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Evento> buscarEventosPorUsuario(Long idUsuario) throws ApiException {
        return getRepository().findByUsuario_Id(idUsuario);
    }

    private Usuario buscarUsuarioLogado() {
        return usuarioRepository.findById(1L).orElseThrow(() -> new ApiException("Usuário não encontrado"));
    }

    private Boolean isUsuarioDono(Long idUsuarioEvento) {
        Usuario usuarioLogado = buscarUsuarioLogado();
        return usuarioLogado.id() == idUsuarioEvento;
    }

    @Override
    public EventoRepository getRepository() {
        return (EventoRepository) super.getRepository();
    }

}
