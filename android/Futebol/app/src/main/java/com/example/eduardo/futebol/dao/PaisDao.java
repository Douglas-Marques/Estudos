package com.example.eduardo.futebol.dao;

import android.support.annotation.NonNull;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.example.eduardo.futebol.model.Pais;
import com.example.eduardo.futebol.util.RealmUtils;

import io.realm.Realm;

public class PaisDao {

    private static final String TAG = "TemporadaDao";

    public static void salvarPais(final String pais) {
        final Realm realmInstance = Realm.getDefaultInstance();
        realmInstance.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                realm.copyToRealm(new Pais(RealmUtils.incrementarId(Pais.class), pais));
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "salvarPais " + pais);
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
