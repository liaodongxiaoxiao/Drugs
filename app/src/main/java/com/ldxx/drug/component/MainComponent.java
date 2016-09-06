package com.ldxx.drug.component;

import com.ldxx.drug.model.MenuModel;
import com.ldxx.drug.module.MainModule;
import com.ldxx.drug.module.MenuListModule;
import com.ldxx.drug.presenter.MainPresenter;
import com.ldxx.drug.scope.ActivityScope;
import com.ldxx.drug.ui.activity.MainActivity;

import dagger.Component;

/**
 * Created by ldxx
 * on 16/8/31.
 */
@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {MainModule.class, MenuListModule.class}
)
public interface MainComponent {
    void inject(MainActivity activity);

    MainPresenter getMainPresenter();

    MenuModel MenuListModel();
}
