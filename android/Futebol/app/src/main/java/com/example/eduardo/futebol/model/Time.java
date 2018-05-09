package com.example.eduardo.futebol.model;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Eduardo on 20/08/2017.
 */

public class Time extends RealmObject {
    @PrimaryKey
    @Index
    private long id;

    private String nome;

    private String escudo;
    //cuidar para deixar nome igual o do drawable do escudo

    private RealmList<String> cores;
    //Cores SEMPRE em letra maiuscula

    private double orcamento;

    private String sigla;

    public Time(long id, String nome, String escudo, RealmList<String> cores, double orcamento, String sigla) {
        this.id = id;
        this.nome = nome;
        this.escudo = escudo;
        this.cores = cores;
        this.orcamento = orcamento;
        this.sigla = sigla;
    }

    public Time() {
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

    public String getEscudo() {
        return escudo;
    }

    public void setEscudo(String escudo) {
        this.escudo = escudo;
    }

    public RealmList<String> getCores() {
        return cores;
    }

    public void setCores(RealmList<String> cores) {
        this.cores = cores;
    }

    public double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(double orcamento) {
        this.orcamento = orcamento;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
