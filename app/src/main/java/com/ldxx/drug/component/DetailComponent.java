package com.ldxx.drug.component;

import com.ldxx.drug.model.MenuModel;
import com.ldxx.drug.module.DetailModel;
import com.ldxx.drug.module.MenuListModule;
import com.ldxx.drug.presenter.DetailPresenter;
import com.ldxx.drug.scope.ActivityScope;
import com.ldxx.drug.ui.activity.DetailActivity;

import dagger.Component;

/**
 * Created by liaodongxiaoxiao on 2016/9/5.
 */
@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {DetailModel.class, MenuListModule.class}
)
public interface DetailComponent {
    void inject(DetailActivity activity);

    DetailPresenter getDetailPresenter();

    MenuModel MenuListModel();
}
