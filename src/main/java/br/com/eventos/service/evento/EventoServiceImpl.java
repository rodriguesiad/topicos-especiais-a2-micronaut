package br.com.eventos.service.evento;

import br.com.eventos.dto.EventoDTO;
import br.com.eventos.exception.ApiException;
import br.com.eventos.entity.Evento;
import br.com.eventos.entity.Usuario;
import br.com.eventos.entity.enums.StatusEvento;
import br.com.eventos.repository.EventoRepository;
import br.com.eventos.repository.UsuarioRepository;
import io.micronaut.security.utils.SecurityService;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class EventoServiceImpl implements EventoService {

    private final EventoRepository eventoRepository;
    private final UsuarioRepository usuarioRepository;
    private final SecurityService securityService;

    public EventoServiceImpl(EventoRepository eventoRepository,
                             UsuarioRepository usuarioRepository,
                             SecurityService securityService) {
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
        this.securityService = securityService;

    }

    @Override
    @Transactional
    public Evento cadastrar(EventoDTO dto) throws ApiException {
        Usuario usuario = buscarUsuarioLogado();
        Evento evento = new Evento(null, dto.nome(), dto.data(), dto.local(), usuario, StatusEvento.ATIVO);
        return this.eventoRepository.save(evento);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Evento> buscarPorId(Long id) throws ApiException {
        return Optional.ofNullable(this.eventoRepository.findById(id)
                .orElseThrow(() -> new ApiException("Evento não encontrado: " + id)));
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Evento> buscarTodos() {
        return this.eventoRepository.findAll();
    }

    @Override
    @Transactional
    public Evento atualizarEvento(Long id, EventoDTO dto) throws ApiException {
        Evento evento = this.eventoRepository.findById(id).orElseThrow(() -> new ApiException("Evento não encontrado"));

        if (!isUsuarioDono(evento.getUsuario().getId())) {
            throw new ApiException("Você não tem permissão para atualizar este evento.");
        }

        evento.setNome(dto.nome());
        evento.setData(dto.data());
        evento.setLocal(dto.local());

        return this.eventoRepository.update(evento);
    }

    @Override
    @Transactional
    public void cancelarEvento(Long id) throws ApiException {
        Evento evento = this.eventoRepository.findById(id).orElseThrow(() -> new ApiException("Evento não encontrado"));

        if (!isUsuarioDono(evento.getUsuario().getId())) {
            throw new ApiException("Você não tem permissão para cancelar este evento.");
        }

        evento.setStatus(StatusEvento.CANCELADO);
        this.eventoRepository.update(evento);
    }

    @Override
    @Transactional
    public void concluirEvento(Long id) throws ApiException {
        Evento evento = this.eventoRepository.findById(id).orElseThrow(() -> new ApiException("Evento não encontrado"));

        if (!isUsuarioDono(evento.getUsuario().getId())) {
            throw new ApiException("Você não tem permissão para concluir este evento.");
        }

        evento.setStatus(StatusEvento.CONCLUIDO);
        this.eventoRepository.save(evento);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Evento> buscarEventosUsuarioLogado() throws ApiException {
        Usuario usuario = buscarUsuarioLogado();
        return this.eventoRepository.findByUsuario_Id(usuario.getId());
    }

    private Usuario buscarUsuarioLogado() {
        return securityService.getAuthentication()
                .flatMap(auth -> {
                    String username = auth.getName();
                    return usuarioRepository.findByEmail(username);
                })
                .orElseThrow(() -> new ApiException("Usuário logado não encontrado"));
    }

    private Boolean isUsuarioDono(Long idUsuarioEvento) {
        Usuario usuarioLogado = buscarUsuarioLogado();
        return usuarioLogado.getId() == idUsuarioEvento;
    }

}
