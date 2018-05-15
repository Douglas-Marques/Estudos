package com.example.eduardo.futebol.dao;

import android.support.annotation.NonNull;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.example.eduardo.futebol.model.Tabela;
import com.example.eduardo.futebol.util.RealmUtils;

import io.realm.Realm;

public class TabelaDao {

    private static final String TAG = "TabelaDao";

    public static void salvarTabela(final Tabela tabela) {
        final Realm realmInstance = Realm.getDefaultInstance();
        realmInstance.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                tabela.setId(RealmUtils.incrementarId(Tabela.class));
                realm.copyToRealm(tabela);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "salvarTabela - SUCESSO");
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
