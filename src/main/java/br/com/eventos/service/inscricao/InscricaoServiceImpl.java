package br.com.eventos.service.inscricao;

import br.com.eventos.dto.InscricaoDTO;
import br.com.eventos.exception.ApiException;
import br.com.eventos.model.Evento;
import br.com.eventos.model.Inscricao;
import br.com.eventos.model.Usuario;
import br.com.eventos.repository.BaseRepository;
import br.com.eventos.repository.EventoRepository;
import br.com.eventos.repository.InscricaoRepository;
import br.com.eventos.repository.UsuarioRepository;
import br.com.eventos.service.BaseServiceImpl;
import jakarta.inject.Singleton;

import java.time.LocalDate;

@Singleton
public class InscricaoServiceImpl extends BaseServiceImpl<Inscricao> implements InscricaoService {

    private final UsuarioRepository usuarioRepository;
    private final EventoRepository eventoRepository;

    public InscricaoServiceImpl(BaseRepository<Inscricao> inscricaoRepository,
                                UsuarioRepository usuarioRepository,
                                EventoRepository eventoRepository) {
        super(inscricaoRepository);
        this.usuarioRepository = usuarioRepository;
        this.eventoRepository = eventoRepository;
    }

    @Override
    public Inscricao cadastrarInscricao(InscricaoDTO dto) throws ApiException {
        Evento evento = eventoRepository.findById(dto.idEvento()).orElseThrow(() -> new ApiException("Evento não encontrado"));
        Usuario usuario = buscarUsuarioLogado();
        Inscricao inscricao = new Inscricao(null, evento, usuario, LocalDate.now(), true);

        return super.cadastrar(inscricao);
    }

    @Override
    public void cancelarInscricao(Long idInscricao) throws ApiException {
        Inscricao inscricao = getRepository().findById(idInscricao).orElseThrow(() -> new ApiException("Inscrição não encontrada"));
        inscricao = new Inscricao(inscricao.id(), inscricao.evento(), inscricao.usuario(), inscricao.dataInscricao(), false);
        getRepository().update(inscricao);
    }

    private Usuario buscarUsuarioLogado() {
        return usuarioRepository.findById(1L).orElseThrow(() -> new ApiException("Usuário não encontrado"));
    }

    @Override
    public Iterable<Inscricao> buscarInscricoesPorEvento(Long eventoId) {
        return getRepository().findByAtivoAndEventoId(true, eventoId);
    }

    @Override
    public InscricaoRepository getRepository() {
        return (InscricaoRepository) super.getRepository();
    }
}
