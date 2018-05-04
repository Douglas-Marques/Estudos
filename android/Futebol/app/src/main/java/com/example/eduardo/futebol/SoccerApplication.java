package com.example.eduardo.futebol;

import android.app.Application;

import io.realm.Realm;

public class SoccerApplication extends Application {

    @Override
    public void onCreate() {
        Realm.init(this);
        super.onCreate();
    }
}
