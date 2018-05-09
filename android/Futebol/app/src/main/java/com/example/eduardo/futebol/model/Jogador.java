package com.example.eduardo.futebol.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Eduardo on 20/08/2017.
 */

public class Jogador extends RealmObject{
    @PrimaryKey
    @Index
    private long id;

    private String nome;

    private int anoNascimento;

    private double overall;

    private double preco;

    private int fimContrato;

    /***FK'S***/
    private Pais pais;

    private Time time;

    public Jogador(long id, String nome, int anoNascimento, double overall, double preco,
                   int fimContrato, Pais pais, Time time) {
        this.id = id;
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.overall = overall;
        this.preco = preco;
        this.fimContrato = fimContrato;
        this.pais = pais;
        this.time = time;
    }

    public Jogador() {
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

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public double getOverall() {
        return overall;
    }

    public void setOverall(double overall) {
        this.overall = overall;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getFimContrato() {
        return fimContrato;
    }

    public void setFimContrato(int fimContrato) {
        this.fimContrato = fimContrato;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
