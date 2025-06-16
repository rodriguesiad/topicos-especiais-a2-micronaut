package br.com.eventos.exception;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.io.IOException;
import java.util.stream.Collectors;

@Produces
@Singleton
@Requires(classes = {Exception.class, ExceptionHandler.class})
public class GlobalExceptionHandler implements ExceptionHandler<Exception, HttpResponse<?>> {

    @Override
    public HttpResponse<?> handle(HttpRequest request, Exception exception) {

        if (exception instanceof ApiException apiException) {
            return HttpResponse.badRequest(new JsonError(apiException.getMessage()));
        }

        if (exception instanceof IllegalArgumentException) {
            return HttpResponse.badRequest(new JsonError(exception.getMessage()));
        }

        if (exception instanceof javax.security.sasl.AuthenticationException) {
            return HttpResponse.unauthorized().body(new JsonError("Falha na autenticação."));
        }

        if (exception instanceof SecurityException) {
            return HttpResponse.status(HttpStatus.FORBIDDEN).body(new JsonError("Acesso negado."));
        }

        if (exception instanceof ConstraintViolationException validation) {
            String errors = validation.getConstraintViolations().stream()
                    .map(violation -> formatViolation(violation))
                    .collect(Collectors.joining("; "));

            return HttpResponse.badRequest(new JsonError(errors));
        }

        if (exception instanceof IOException io) {
            return HttpResponse.badRequest(new JsonError("Erro de IO: " + io.getMessage()));
        }

        return HttpResponse.serverError(
                new JsonError("Erro interno no servidor: " + exception.getMessage())
        );
    }

    private String formatViolation(ConstraintViolation<?> violation) {
        return violation.getPropertyPath() + " " + violation.getMessage();
    }
}
