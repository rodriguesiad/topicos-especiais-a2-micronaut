package br.com.eventos.service.inscricao;

import br.com.eventos.model.Inscricao;
import br.com.eventos.repository.BaseRepository;
import br.com.eventos.service.BaseServiceImpl;
import jakarta.inject.Singleton;

@Singleton
public class InscricaoServiceImpl extends BaseServiceImpl<Inscricao> implements InscricaoService {

    public InscricaoServiceImpl(BaseRepository<Inscricao> inscricaoRepository) {
        super(inscricaoRepository);
    }

}
