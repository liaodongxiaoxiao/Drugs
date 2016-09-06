package com.ldxx.drug.module;


import com.ldxx.drug.model.MenuModel;
import com.ldxx.drug.presenter.MainPresenter;
import com.ldxx.drug.presenter.impl.MainPresenterImpl;
import com.ldxx.drug.ui.view.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liaodongxiaoxiao
 * on 2016/9/1.
 */
@Module
public class MainModule {
    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Provides
    public MainView provideView() {
        return view;
    }

    @Provides
    public MainPresenter providePresenter(MainView mainView, MenuModel menuListModel) {
        return new MainPresenterImpl(mainView, menuListModel);
    }
}
