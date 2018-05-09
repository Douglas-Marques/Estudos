package com.example.eduardo.futebol.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Jogo extends RealmObject {
    @PrimaryKey
    @Index
    private long id;

    private Date dataJogo;

    private int golsMandante;

    private int golsVisitante;

    /***FK'S***/

    private Rodada rodada;

    private Time timeMandante;

    private Time timeVisitante;

    private Status status;

    public Jogo(long id, Date dataJogo, int golsMandante, int golsVisitante, Rodada rodada,
                Time timeMandante, Time timeVisitante, Status status) {
        this.id = id;
        this.dataJogo = dataJogo;
        this.golsMandante = golsMandante;
        this.golsVisitante = golsVisitante;
        this.rodada = rodada;
        this.timeMandante = timeMandante;
        this.timeVisitante = timeVisitante;
        this.status = status;
    }

    public Jogo () {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDataJogo() {
        return dataJogo;
    }

    public void setDataJogo(Date dataJogo) {
        this.dataJogo = dataJogo;
    }

    public int getGolsMandante() {
        return golsMandante;
    }

    public void setGolsMandante(int golsMandante) {
        this.golsMandante = golsMandante;
    }

    public int getGolsVisitante() {
        return golsVisitante;
    }

    public void setGolsVisitante(int golsVisitante) {
        this.golsVisitante = golsVisitante;
    }

    public Rodada getRodada() {
        return rodada;
    }

    public void setRodada(Rodada rodada) {
        this.rodada = rodada;
    }

    public Time getTimeMandante() {
        return timeMandante;
    }

    public void setTimeMandante(Time timeMandante) {
        this.timeMandante = timeMandante;
    }

    public Time getTimeVisitante() {
        return timeVisitante;
    }

    public void setTimeVisitante(Time timeVisitante) {
        this.timeVisitante = timeVisitante;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
