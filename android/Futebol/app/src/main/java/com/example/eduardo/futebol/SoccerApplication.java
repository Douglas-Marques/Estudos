package com.example.eduardo.futebol;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import io.realm.Realm;

public class SoccerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        //Fabric.with(this, new Crashlytics());

        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)
                .build();
        Fabric.with(fabric);
    }//https://gist.github.com/carloseduardosx/6c3a3dfc3a9cb467aa464884f39544c2
}

/*//TODO pegar ultimos ids dos objetos
public class MyApplication extends Application {

  public static AtomicLong primaryKeyValue;

  public void onCreate() {
    RealmConfiguration config = new RealmConfiguration.Builder(context).build();
    Realm realm = Realm.getInstance(config);
    primaryKeyValue = new AtomicLong(realm.where(Foo.class).max("id").longValue());
    realm.close();
  }
}

public class MyActivity extends Activity {

  protected void onCreate() {
    long nextKey = MyApplication.primaryKeyValue.incrementAndGet();
  }
}

////////////////////outra parada
public class PrimaryKeyFactory {

    private static Map<Class, AtomicLong> keys = new HashMap<Class, AtomicLong>();

    public static void initialize(Realm realm) {
        // 1. Loop through all classes using RealmSchema / RealmObjectSchema / RealmConfiguration.getRealmObjectClassees()
        // 2. Determine the maximum value for each primary key field and save it in the `keys` map.
    }

    // Automitically create next key
    public synchronized long nextKey(Class clazz) {
        return keys.get(clazz).incrementAndGet();
    }
}*/