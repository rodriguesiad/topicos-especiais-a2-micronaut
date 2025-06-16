package br.com.eventos.dto;

import br.com.eventos.entity.Usuario;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record UsuarioResponseDTO(
        String nome,
        String email
) {
    public static UsuarioResponseDTO toResponse(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}