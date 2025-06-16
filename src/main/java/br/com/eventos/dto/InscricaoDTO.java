package br.com.eventos.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Introspected
@Serdeable
public record InscricaoDTO(
        @NotNull(message = "O campo evento é obrigatório") Long idEvento
) {
}
