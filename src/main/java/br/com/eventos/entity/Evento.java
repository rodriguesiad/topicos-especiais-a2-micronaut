package br.com.eventos.entity;

import br.com.eventos.entity.enums.StatusEvento;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;

import java.time.LocalDate;

@MappedEntity("evento")
public class Evento {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String nome;

    @NonNull
    private LocalDate data;

    @NonNull
    private String local;

    @NonNull
    @Relation(Relation.Kind.MANY_TO_ONE)
    private Usuario usuario;

    @NonNull
    private StatusEvento status;

    public Evento() {
    }

    public Evento(Long id, String nome, LocalDate data, String local, Usuario usuario, StatusEvento status) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.local = local;
        this.usuario = usuario;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public StatusEvento getStatus() {
        return status;
    }

    public void setStatus(StatusEvento status) {
        this.status = status;
    }
}
