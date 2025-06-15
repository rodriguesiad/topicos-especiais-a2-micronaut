package br.com.eventos.model;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Serdeable
@MappedEntity("evento")
public record Evento(
        @Id
        @GeneratedValue
        Long id,
        @NotNull(message = "O nome do evento é obrigatório")
        String nome,

        @NotNull(message = "A data do evento é obrigatória")
        LocalDate data,

        String local,

        @NotNull(message = "O usuário organizador é obrigatório")
        @Relation(value = Relation.Kind.MANY_TO_ONE)
        Usuario usuario,

        @NotNull
        Boolean ativo
) {
}
