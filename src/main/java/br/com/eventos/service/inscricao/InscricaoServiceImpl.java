package br.com.eventos.service.inscricao;

import br.com.eventos.repository.InscricaoRepository;
import jakarta.inject.Singleton;

@Singleton
public class InscricaoServiceImpl implements InscricaoService {
    private final InscricaoRepository inscricaoRepository;

    public InscricaoServiceImpl(InscricaoRepository inscricaoRepository) {
        this.inscricaoRepository = inscricaoRepository;
    }

}
