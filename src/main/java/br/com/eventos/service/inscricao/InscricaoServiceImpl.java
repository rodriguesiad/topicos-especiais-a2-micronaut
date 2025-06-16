package br.com.eventos.service.inscricao;

import br.com.eventos.dto.InscricaoDTO;
import br.com.eventos.entity.enums.StatusEvento;
import br.com.eventos.exception.ApiException;
import br.com.eventos.entity.Evento;
import br.com.eventos.entity.Inscricao;
import br.com.eventos.entity.Usuario;
import br.com.eventos.repository.EventoRepository;
import br.com.eventos.repository.InscricaoRepository;
import br.com.eventos.repository.UsuarioRepository;
import io.micronaut.security.utils.SecurityService;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;

import java.time.LocalDate;
import java.util.Optional;

@Singleton
public class InscricaoServiceImpl implements InscricaoService {

    private final InscricaoRepository inscricaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EventoRepository eventoRepository;
    private final SecurityService securityService;

    public InscricaoServiceImpl(InscricaoRepository inscricaoRepository,
                                UsuarioRepository usuarioRepository,
                                EventoRepository eventoRepository,
                                SecurityService securityService) {
        this.inscricaoRepository = inscricaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.eventoRepository = eventoRepository;
        this.securityService = securityService;
    }

    @Override
    @Transactional
    public Inscricao cadastrar(InscricaoDTO dto) throws ApiException {
        Evento evento = eventoRepository.findById(dto.idEvento()).orElseThrow(() -> new ApiException("Evento não encontrado"));

        if (evento.getStatus().equals(StatusEvento.CANCELADO)){
            throw new ApiException("O evento foi cancelado. Não é possível realizar a inscrição.");
        }

        if (evento.getStatus().equals(StatusEvento.CONCLUIDO)){
            throw new ApiException("O evento já foi concluído. Não é possível realizar a inscrição.");
        }

        Usuario usuario = buscarUsuarioLogado();

        Optional<Inscricao> inscricaoExistente = inscricaoRepository
                .findByEventoIdAndUsuarioIdAndAtivoTrue(evento.getId(), usuario.getId());

        if (inscricaoExistente.isPresent()) {
            throw new ApiException("Você já está inscrito neste evento.");
        }

        Inscricao inscricao = new Inscricao(null, evento, usuario, LocalDate.now(), true);

        return this.inscricaoRepository.save(inscricao);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Inscricao> buscarPorId(Long id) throws ApiException {
        return Optional.ofNullable(this.inscricaoRepository.findById(id)
                .orElseThrow(() -> new ApiException("Inscrição não encontrada: " + id)));
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Inscricao> buscarTodos() {
        return this.inscricaoRepository.findAll();
    }

    @Override
    @Transactional
    public void cancelarInscricao(Long idInscricao) throws ApiException {
        Inscricao inscricao = this.inscricaoRepository.findById(idInscricao).orElseThrow(() -> new ApiException("Inscrição não encontrada"));

        if (!isUsuarioDono(inscricao.getUsuario().getId())) {
            throw new ApiException("Você não tem permissão para cancelar esta inscrição.");
        }

        inscricao.setAtivo(false);
        this.inscricaoRepository.update(inscricao);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Inscricao> buscarInscricoesUsuarioLogado() throws ApiException {
        Usuario usuario = buscarUsuarioLogado();
        return this.inscricaoRepository.findByUsuario_Id(usuario.getId());    }

    @Override
    public Iterable<Inscricao> buscarInscricoesPorEvento(Long eventoId) {
        return this.inscricaoRepository.findByAtivoAndEventoId(true, eventoId);
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
