package com.example.eduardo.futebol.dao;

import android.support.annotation.NonNull;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.example.eduardo.futebol.model.Posicao;

import java.util.ArrayList;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmResults;

public class PosicaoDao {

    private static final String TAG = "PosicaoDao";

    public static void salvarPosicoes() {
        final Realm realmInstance = Realm.getDefaultInstance();
        realmInstance.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                realm.copyToRealm(criarPosicoes());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "salvarPosicoes - SUCESSO");
                realmInstance.close();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(@NonNull Throwable error) {
                Log.e(TAG, error.getMessage());
                Crashlytics.logException(error);
                realmInstance.close();
            }
        });
    }

    public static ArrayList<Posicao> obterPosicoes() {
        return new ArrayList<>(Realm.getDefaultInstance().where(Posicao.class).findAll());
    }

    public static Posicao obterPosicaoPorSigla(String sigla) {
        return Realm.getDefaultInstance().where(Posicao.class).equalTo("sigla", sigla).findFirst();
    }

    private static ArrayList<Posicao> criarPosicoes() {
        int id = 0;
        final Posicao goleiro = new Posicao(id++, "GO", "Goleiro");
        final Posicao zagueiro = new Posicao(id++, "ZG", "Zagueiro");
        final Posicao lateralEsquerdo = new Posicao(id++, "LE", "Lateral esquerdo");
        final Posicao lateralDireito = new Posicao(id++, "LD", "Lateral direito");
        final Posicao volante = new Posicao(id++, "VO", "Volante");
        final Posicao meiaCentral = new Posicao(id++, "MC", "Meia central");
        final Posicao meiaEsquerda = new Posicao(id++, "ME", "Meia esquerda");
        final Posicao meiaDireita = new Posicao(id++, "MD", "Meia direita");
        final Posicao meiaAtacante = new Posicao(id++, "MA", "Meia atacante");
        final Posicao pontaEsquerda = new Posicao(id++, "PE", "Ponta esquerda");
        final Posicao pontaDireita = new Posicao(id++, "PD", "Ponta direita");
        final Posicao atacante = new Posicao(id, "AT", "Atacante");

        return new ArrayList<Posicao>() {{
            add(goleiro);
            add(zagueiro);
            add(lateralEsquerdo);
            add(lateralDireito);
            add(volante);
            add(meiaCentral);
            add(meiaEsquerda);
            add(meiaDireita);
            add(meiaAtacante);
            add(pontaEsquerda);
            add(pontaDireita);
            add(atacante);
        }};
    }
}
