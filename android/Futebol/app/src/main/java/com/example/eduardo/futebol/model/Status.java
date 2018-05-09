package com.example.eduardo.futebol.model;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Status extends RealmObject {

    @Ignore
    public final static String JOGO_AGENDADO = "AGENDADO";

    @Ignore
    public final static String JOGO_FINALIZADO = "FINALIZADO";

    @PrimaryKey
    @Index
    private long id;

    private String status;
    //AGENDADO
    //FINALIZADO

    public Status(long id, String status) {
        this.id = id;
        this.status = status;
    }

    public Status() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
