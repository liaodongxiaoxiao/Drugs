package com.ldxx.drug.component;

import com.ldxx.drug.module.SplashModule;
import com.ldxx.drug.scope.ActivityScope;
import com.ldxx.drug.ui.activity.SplashActivity;

import dagger.Component;

/**
 * Created by liaodongxiaoxiao
 * on 2016/9/5.
 */
@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {SplashModule.class}
)
public interface SplashComponent {
    void inject(SplashActivity activity);
}
