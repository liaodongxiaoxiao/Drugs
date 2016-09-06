package com.ldxx.drug.module;

import android.app.Application;

import com.ldxx.drug.app.DrugApplication;
import com.ldxx.drug.utils.AppUtils;
import com.ldxx.drug.utils.ToastUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private static final String TAG = "AppModule";
    private DrugApplication application;

    public AppModule(DrugApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    public AppUtils provideAppUtils() {
        return new AppUtils(application);
    }

    @Provides
    @Singleton
    public ToastUtil provideToastUtil() {
        return new ToastUtil(application);
    }
}
