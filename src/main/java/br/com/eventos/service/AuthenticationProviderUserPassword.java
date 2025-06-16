package br.com.eventos.service;

import br.com.eventos.entity.Usuario;
import br.com.eventos.service.usuario.UsuarioService;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.provider.HttpRequestAuthenticationProvider;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
class AuthenticationProviderUserPassword<B> implements HttpRequestAuthenticationProvider<B> {

    private final UsuarioService usuarioService;

    @Inject
    public AuthenticationProviderUserPassword(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public @NonNull AuthenticationResponse authenticate(io.micronaut.http.@Nullable HttpRequest<B> requestContext, @NonNull AuthenticationRequest<String, String> authRequest) {
        String email = authRequest.getIdentity();
        String senha = authRequest.getSecret();

        Usuario usuario = usuarioService.buscarPorEmail(email);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            return AuthenticationResponse.success(usuario.getEmail());
        } else {
            return AuthenticationResponse.failure(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH);
        }
    }

    @Override
    public @NonNull AuthenticationResponse authenticate(@NonNull AuthenticationRequest<String, String> authRequest) {
        String email = authRequest.getIdentity();
        String senha = authRequest.getSecret();

        Usuario usuario = usuarioService.buscarPorEmail(email);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            return AuthenticationResponse.success(usuario.getEmail());
        } else {
            return AuthenticationResponse.failure(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH);
        }
    }
}