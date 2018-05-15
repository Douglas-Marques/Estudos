package com.example.eduardo.futebol.dao;

import android.support.annotation.NonNull;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.example.eduardo.futebol.model.Jogo;
import com.example.eduardo.futebol.util.RealmUtils;

import io.realm.Realm;

public class JogoDao {

    private static final String TAG = "JogoDao";

    public static void salvarJogo(final Jogo jogo) {
        final Realm realmInstance = Realm.getDefaultInstance();
        realmInstance.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                jogo.setId(RealmUtils.incrementarId(Jogo.class));
                realm.copyToRealm(jogo);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "salvarJogo - SUCESSO");
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
