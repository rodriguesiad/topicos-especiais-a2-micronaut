package br.com.eventos.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record InscricaoDTO(
        @NotNull(message = "O campo evento é obrigatório") Long idEvento
) {
}
