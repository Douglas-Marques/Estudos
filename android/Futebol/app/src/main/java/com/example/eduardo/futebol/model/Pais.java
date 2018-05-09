package com.example.eduardo.futebol.model;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Pais extends RealmObject {
    @PrimaryKey
    @Index
    private long id;

    private String nome;

    public Pais(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Pais() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
