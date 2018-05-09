package com.example.eduardo.futebol.model;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class JogadorPosicao extends RealmObject {
    @PrimaryKey
    @Index
    private long id;

    /***FK'S***/
    private Jogador jogador;

    private Posicao posicao;

    public JogadorPosicao(long id, Jogador jogador, Posicao posicao) {
        this.id = id;
        this.jogador = jogador;
        this.posicao = posicao;
    }

    public JogadorPosicao() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }
}
