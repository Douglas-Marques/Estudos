package com.example.eduardo.futebol.util;

import io.realm.Realm;
import io.realm.RealmObject;

public class RealmUtils {

    public static int incrementarId(Class<? extends RealmObject> clazz) {
        Number num = Realm.getDefaultInstance().where(clazz).max("id");
        int nextID;
        if(num == null) {
            nextID = 1;
        } else {
            nextID = num.intValue() + 1;
        }

        return nextID;
    }
}
