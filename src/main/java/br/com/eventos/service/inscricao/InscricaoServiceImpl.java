package br.com.eventos.service.inscricao;

import br.com.eventos.dto.InscricaoDTO;
import br.com.eventos.exception.ApiException;
import br.com.eventos.model.Evento;
import br.com.eventos.model.Inscricao;
import br.com.eventos.model.Usuario;
import br.com.eventos.repository.EventoRepository;
import br.com.eventos.repository.InscricaoRepository;
import br.com.eventos.repository.UsuarioRepository;
import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;

import java.time.LocalDate;
import java.util.Optional;

@Singleton
public class InscricaoServiceImpl implements InscricaoService {

    private final InscricaoRepository inscricaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EventoRepository eventoRepository;

    public InscricaoServiceImpl(InscricaoRepository inscricaoRepository,
                                UsuarioRepository usuarioRepository,
                                EventoRepository eventoRepository) {
        this.inscricaoRepository = inscricaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.eventoRepository = eventoRepository;
    }

    @Override
    @Transactional
    public Inscricao cadastrar(InscricaoDTO dto) throws ApiException {
        Evento evento = eventoRepository.findById(dto.idEvento()).orElseThrow(() -> new ApiException("Evento não encontrado"));
        Usuario usuario = buscarUsuarioLogado();
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
        inscricao = new Inscricao(inscricao.id(), inscricao.evento(), inscricao.usuario(), inscricao.dataInscricao(), false);
        this.inscricaoRepository.update(inscricao);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Inscricao> buscarInscricoesUsuario(Long idUsuario) throws ApiException {
        return this.inscricaoRepository.findByUsuario_Id(idUsuario);
    }

    private Usuario buscarUsuarioLogado() {
        return usuarioRepository.findById(1L).orElseThrow(() -> new ApiException("Usuário não encontrado"));
    }

    @Override
    public Iterable<Inscricao> buscarInscricoesPorEvento(Long eventoId) {
        return this.inscricaoRepository.findByAtivoAndEventoId(true, eventoId);
    }

}
