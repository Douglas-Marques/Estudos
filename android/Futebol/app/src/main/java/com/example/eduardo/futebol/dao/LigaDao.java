package com.example.eduardo.futebol.dao;

import android.support.annotation.NonNull;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.example.eduardo.futebol.model.Liga;
import com.example.eduardo.futebol.model.Pais;
import com.example.eduardo.futebol.model.Temporada;
import com.example.eduardo.futebol.util.RealmUtils;

import io.realm.Realm;

public class LigaDao {

    private static final String TAG = "LigaDao";

    public static void salvarLiga(final String nome, final String sigla, final Temporada temporada, final Pais pais) {
        final Realm realmInstance = Realm.getDefaultInstance();

        realmInstance.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                realm.copyToRealm(new Liga(RealmUtils.incrementarId(Liga.class), nome, sigla, temporada, pais));
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "salvarLiga " + nome);
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
