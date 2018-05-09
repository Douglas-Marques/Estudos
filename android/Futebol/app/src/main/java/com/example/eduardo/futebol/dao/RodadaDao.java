package com.example.eduardo.futebol.dao;

import android.support.annotation.NonNull;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.example.eduardo.futebol.model.Liga;
import com.example.eduardo.futebol.model.Rodada;
import com.example.eduardo.futebol.util.RealmUtils;

import io.realm.Realm;

public class RodadaDao {

    private final static String TAG = "RodadaDao";

    public static void salvarRodada(final int numeroRodada, final Liga liga) {
        final Realm realmInstance = Realm.getDefaultInstance();
        realmInstance.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                realm.copyToRealm(new Rodada(RealmUtils.incrementarId(Rodada.class), numeroRodada, liga));
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "salvarRodada " + numeroRodada);
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
