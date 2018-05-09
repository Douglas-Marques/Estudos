package com.example.eduardo.futebol.model;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Rodada extends RealmObject {
    @PrimaryKey
    @Index
    private long id;

    private int numeroRodada;

    private Liga liga;

    public Rodada(long id, int numeroRodada, Liga liga) {
        this.id = id;
        this.numeroRodada = numeroRodada;
        this.liga = liga;
    }

    public Rodada() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumeroRodada() {
        return numeroRodada;
    }

    public void setNumeroRodada(int numeroRodada) {
        this.numeroRodada = numeroRodada;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }
}
