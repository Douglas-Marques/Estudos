package com.example.eduardo.futebol.model;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Tabela extends RealmObject {
    @PrimaryKey
    @Index
    private long id;

    private Time time;

    private Liga liga;

    private int pontos;

    private int jogos;

    private int vitorias;

    private int empates;

    private int derrotas;

    private int golsPro;

    private int golsContra;

    public Tabela(long id, Time time, Liga liga, int pontos, int jogos, int vitorias,
                  int empates, int derrotas, int golsPro, int golsContra) {
        this.id = id;
        this.time = time;
        this.liga = liga;
        this.pontos = pontos;
        this.jogos = jogos;
        this.vitorias = vitorias;
        this.empates = empates;
        this.derrotas = derrotas;
        this.golsPro = golsPro;
        this.golsContra = golsContra;
    }

    public Tabela() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getJogos() {
        return jogos;
    }

    public void setJogos(int jogos) {
        this.jogos = jogos;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public int getGolsPro() {
        return golsPro;
    }

    public void setGolsPro(int golsPro) {
        this.golsPro = golsPro;
    }

    public int getGolsContra() {
        return golsContra;
    }

    public void setGolsContra(int golsContra) {
        this.golsContra = golsContra;
    }
}
