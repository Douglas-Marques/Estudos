package com.example.eduardo.futebol.model;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Eduardo on 20/08/2017.
 */

public class Liga extends RealmObject{
    @PrimaryKey
    @Index
    private long id;

    private String nome;

    private String sigla;

    /***FK'S***/

    private Temporada temporada;

    private Pais pais;

    public Liga(long id, String nome, String sigla, Temporada temporada, Pais pais) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.temporada = temporada;
        this.pais = pais;
    }

    public Liga() {
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
