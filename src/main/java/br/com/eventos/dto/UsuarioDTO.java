package br.com.eventos.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Introspected
@Serdeable
public record UsuarioDTO(
        @NotNull(message = "O nome é obrigatório")
        String nome,

        @Email
        @NotNull(message = "O e-mail é obrigatório")
        String email,

        @NotNull(message = "A senha é obrigatória")
        String senha
) {
}
