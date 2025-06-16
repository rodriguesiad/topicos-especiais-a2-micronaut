package br.com.eventos.dto;

import br.com.eventos.entity.Inscricao;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDate;

@Introspected
@Serdeable
public record InscricaoResponseDTO(
        Long id,
        EventoResponseDTO evento,
        UsuarioResponseDTO usuario,
        LocalDate dataInscricao,
        Boolean ativo
) {
    public static InscricaoResponseDTO toResponse(Inscricao inscricao) {
        return new InscricaoResponseDTO(
                inscricao.getId(),
                EventoResponseDTO.toResponse(inscricao.getEvento()),
                UsuarioResponseDTO.toResponse(inscricao.getUsuario()),
                inscricao.getDataInscricao(),
                inscricao.getAtivo()
        );
    }
}