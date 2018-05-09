package com.example.eduardo.futebol.model;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

public class Posicao extends RealmObject {
    @PrimaryKey
    @Index
    private long id;

    private String sigla;

    private String nome;

    //TODO ver se precisa desse metodo q faz a relacao one to many invertida para obter resultados
    //Isso n sera mais necessario mas fica aqui para eu poder usar em outros lugares
    /*@LinkingObjects("posicoes")
    private final RealmResults<Jogador> jogadores;*/

    public Posicao() {
    }

    public Posicao(long id, String sigla, String nome) {
        this.id = id;
        this.sigla = sigla;
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

}
