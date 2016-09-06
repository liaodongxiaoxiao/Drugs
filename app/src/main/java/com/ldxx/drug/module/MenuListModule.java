package com.ldxx.drug.module;

import android.content.Context;


import com.ldxx.drug.api.MenuApi;
import com.ldxx.drug.model.MenuModel;
import com.ldxx.drug.model.impl.MenuModelImpl;
import com.ldxx.drug.utils.RetrofitManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liaodongxiaoxiao
 * on 2016/9/1.
 */
@Module
public class MenuListModule {
    private Context context;

    public MenuListModule(Context context) {
        this.context = context;
    }

    @Provides
    MenuModel provideMenuListModel(MenuApi api) {
        return new MenuModelImpl(api);
    }

    @Provides
    MenuApi provideMyApi(RetrofitManager manager) {
        return manager.getService(MenuApi.class);
    }

    @Provides
    RetrofitManager provideRetrofitManager() {
        return new RetrofitManager(context);
    }

}
