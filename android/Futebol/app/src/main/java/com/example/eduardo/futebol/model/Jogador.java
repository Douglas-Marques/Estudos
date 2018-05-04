package com.example.eduardo.futebol.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Eduardo on 20/08/2017.
 */

public class Jogador extends RealmObject{
    @PrimaryKey
    private String id;

    @Required
    private String nome;

    @Required
    private RealmList<Posicao> posicoes;
}
