package com.ldxx.drug.app;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.ldxx.drug.component.AppComponent;
import com.ldxx.drug.component.DaggerAppComponent;
import com.ldxx.drug.module.AppModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by liaodongxiaoxiao
 * on 2016/8/31.
 */

public class DrugApplication extends Application {
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        setupGraph();
        initRealm(this);
    }

    private void initRealm(Context context) {
        RealmConfiguration config = new RealmConfiguration.Builder(context)
                .name("menu.realm")
                //.encryptionKey(getKey())
                .schemaVersion(0)
                //.modules(new MySchemaModule())
                //.migration(new MyMigration())
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private void setupGraph() {
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        component.inject(this);
    }

    public AppComponent component() {
        return component;
    }

    public static DrugApplication get(Context context) {
        return (DrugApplication) context.getApplicationContext();
    }
}
