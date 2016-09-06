package com.ldxx.drug.component;


import android.app.Application;

import com.ldxx.drug.app.DrugApplication;
import com.ldxx.drug.module.AppModule;
import com.ldxx.drug.utils.AppUtils;
import com.ldxx.drug.utils.ToastUtil;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(DrugApplication app);

    Application getApplication();

    AppUtils getAppUtils();

    ToastUtil getToastUtil();
}