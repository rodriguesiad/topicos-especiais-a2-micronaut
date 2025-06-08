package br.com.eventos.model;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Serdeable
@MappedEntity("usuario")
public record Usuario(
        @Id
        @GeneratedValue
        Long id,

        @NotNull(message = "O nome é obrigatório")
        String nome,

        @Email
        @NotNull(message = "O e-mail é obrigatório")
        String email,

        @NotNull(message = "A senha é obrigatória")
        String senha) {
}
