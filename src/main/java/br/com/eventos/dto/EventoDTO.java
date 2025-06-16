package br.com.eventos.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Introspected
@Serdeable
public record EventoDTO(
        @NotNull(message = "O campo nome é obrigatório") String nome,
        @NotNull(message = "O campo data é obrigatório") LocalDate data,
        @NotNull(message = "O campo local é obrigatório") String local
) {
}
