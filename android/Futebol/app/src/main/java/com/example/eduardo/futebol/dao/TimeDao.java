package com.example.eduardo.futebol.dao;

import android.support.annotation.NonNull;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.example.eduardo.futebol.model.Time;
import com.example.eduardo.futebol.util.RealmUtils;

import io.realm.Realm;

public class TimeDao {

    private final static String TAG = "TimeDao";

    public static void salvarTime(final Time time) {
        final Realm realmInstance = Realm.getDefaultInstance();
        realmInstance.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                time.setId(RealmUtils.incrementarId(Time.class));
                realm.copyToRealm(time);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "salvarTime " + time.getNome());
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
