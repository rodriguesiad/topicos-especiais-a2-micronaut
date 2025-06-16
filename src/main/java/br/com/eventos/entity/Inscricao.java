package br.com.eventos.entity;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@MappedEntity("inscricao")
public class Inscricao {
    @Id
    @GeneratedValue
    Long id;

    @NonNull
    @Relation(value = Relation.Kind.MANY_TO_ONE)
    Evento evento;

    @NonNull
    @Relation(value = Relation.Kind.MANY_TO_ONE)
    Usuario usuario;

    @NonNull
    LocalDate dataInscricao;

    @NotNull
    Boolean ativo;

    public Inscricao() {
    }

    public Inscricao(Long id, Evento evento, Usuario usuario, LocalDate dataInscricao, Boolean ativo) {
        this.id = id;
        this.evento = evento;
        this.usuario = usuario;
        this.dataInscricao = dataInscricao;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataInscricao() {
        return dataInscricao;
    }

    public void setDataInscricao(LocalDate dataInscricao) {
        this.dataInscricao = dataInscricao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}