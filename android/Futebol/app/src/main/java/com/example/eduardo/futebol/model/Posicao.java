package com.example.eduardo.futebol.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Posicao extends RealmObject {
    @PrimaryKey
    private String id;

    @Required
    private String sigla;

    @Required
    private String nome;

    @Required
    private String descricao;

    //TODO ver se precisa desse metodo q faz a relacao ont to many invertida para obter resultados
    /*@LinkingObjects("posicoes")
    private final RealmResults<Jogador> jogadores;*/

    public Posicao(String id, String sigla, String nome, String descricao) {
        this.id = id;
        this.sigla = sigla;
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
