package com.example.eduardo.futebol.dao;

import android.support.annotation.NonNull;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.example.eduardo.futebol.model.Jogador;
import com.example.eduardo.futebol.util.RealmUtils;

import io.realm.Realm;

public class JogadorDao {

    private final static String TAG = "JogadorDao";

    public static void salvarJogador(final Jogador jogador) {
        final Realm realmInstance = Realm.getDefaultInstance();
        realmInstance.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                jogador.setId(RealmUtils.incrementarId(Jogador.class));
                realm.copyToRealm(jogador);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "salvarJogador " + jogador.getNome());
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
