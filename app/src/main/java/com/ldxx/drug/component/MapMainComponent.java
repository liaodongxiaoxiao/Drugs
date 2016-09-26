package com.ldxx.drug.component;

import com.ldxx.drug.model.MenuModel;
import com.ldxx.drug.model.StoreModel;
import com.ldxx.drug.module.MapMainModule;
import com.ldxx.drug.module.MenuListModule;
import com.ldxx.drug.presenter.MainPresenter;
import com.ldxx.drug.presenter.MapMainPresenter;
import com.ldxx.drug.scope.ActivityScope;
import com.ldxx.drug.ui.activity.MainActivity;
import com.ldxx.drug.ui.activity.MapMainActivity;

import dagger.Component;

/**
 * Created by wangzhuo-neu
 * on 2016/9/26.
 */
@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {MapMainModule.class}
)
public interface MapMainComponent {
    void inject(MapMainActivity activity);

    MapMainPresenter getMapMainPresenter();

    StoreModel getStoreModel();
}
