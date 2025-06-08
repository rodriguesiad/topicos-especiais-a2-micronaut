package br.com.eventos.model;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Serdeable
@MappedEntity("inscricao")
public record Inscricao(
        @Id @GeneratedValue Long id,

        @NotNull
        @Relation(value = Relation.Kind.MANY_TO_ONE)
        Evento evento,

        @NotNull
        @Relation(value = Relation.Kind.MANY_TO_ONE)
        Usuario usuario,

        @NotNull(message = "A data da inscrição é obrigatória")
        LocalDate dataInscricao,

        @Nullable
        String status
) {}