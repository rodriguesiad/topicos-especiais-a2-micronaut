package br.com.eventos.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EventoDTO(
        @NotNull(message = "O campo nome é obrigatório") String nome,
        @NotNull(message = "O campo data é obrigatório") LocalDate data,
        String local
) {
}
