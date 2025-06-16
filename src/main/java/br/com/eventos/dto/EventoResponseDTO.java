package br.com.eventos.dto;

import br.com.eventos.entity.Evento;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDate;

@Introspected
@Serdeable
public record EventoResponseDTO(
        Long id,
        String nome,
        LocalDate data,
        String local,
        UsuarioResponseDTO usuario,
        String status
) {
    public static EventoResponseDTO toResponse(Evento evento) {
        return new EventoResponseDTO(
                evento.getId(),
                evento.getNome(),
                evento.getData(),
                evento.getLocal(),
                UsuarioResponseDTO.toResponse(evento.getUsuario()),
                evento.getStatus().name()
        );
    }
}