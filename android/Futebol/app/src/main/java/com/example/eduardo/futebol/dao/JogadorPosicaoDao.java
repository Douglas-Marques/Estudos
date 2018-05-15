package com.example.eduardo.futebol.dao;

import android.support.annotation.NonNull;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.example.eduardo.futebol.model.Jogador;
import com.example.eduardo.futebol.model.JogadorPosicao;
import com.example.eduardo.futebol.model.Posicao;
import com.example.eduardo.futebol.util.RealmUtils;

import io.realm.Realm;

public class JogadorPosicaoDao {

    private static final String TAG = "JogadorPosicaoDao";

    public static void salvarJogadorPosicao(final Jogador jogador, final Posicao posicao) {
        final Realm realmInstance = Realm.getDefaultInstance();
        realmInstance.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                realm.copyToRealm(new JogadorPosicao(RealmUtils.incrementarId(JogadorPosicao.class), jogador, posicao));
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "jogadorPosicao - SUCESSO");
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
}
