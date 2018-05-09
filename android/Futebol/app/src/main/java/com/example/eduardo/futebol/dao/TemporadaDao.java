package com.example.eduardo.futebol.dao;

import android.support.annotation.NonNull;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.example.eduardo.futebol.model.Temporada;
import com.example.eduardo.futebol.util.RealmUtils;

import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;

public class TemporadaDao {

    private static final String TAG = "TemporadaDao";

    public static void salvarTemporada(final int temporada) {
        final Realm realmInstance = Realm.getDefaultInstance();
        realmInstance.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                realm.copyToRealm(new Temporada(RealmUtils.incrementarId(Temporada.class), temporada));
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "salvarTemporada " + temporada);
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
