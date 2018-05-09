package com.example.eduardo.futebol.model;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Temporada extends RealmObject {
    @PrimaryKey
    @Index
    private long id;
    private int ano;

    public Temporada(long id, int ano) {
        this.id = id;
        this.ano = ano;
    }

    public Temporada() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
