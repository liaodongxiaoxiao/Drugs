package com.ldxx.drug.module;

import android.content.Context;
import android.util.Log;

import com.ldxx.drug.api.MenuApi;
import com.ldxx.drug.model.StoreModel;
import com.ldxx.drug.model.impl.StoreModelImpl;
import com.ldxx.drug.presenter.MapMainPresenter;
import com.ldxx.drug.presenter.impl.MapMainPresenterImpl;
import com.ldxx.drug.ui.view.MapMainView;
import com.ldxx.drug.utils.RetrofitManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wangzhuo-neu
 * on 2016/9/26.
 */
@Module
public class MapMainModule {
    private static final String TAG = "MapMainModule";
    private Context context;
    private MapMainView mapMainView;

    public MapMainModule(MapMainView mapMainView, Context context) {
        Log.e(TAG, "MapMainModule: " );
        this.mapMainView = mapMainView;
        this.context = context;
    }

    @Provides
    public MapMainView provideMapMainView() {
        return mapMainView;
    }

    @Provides
    public MapMainPresenter provideMapMainPresenter(StoreModel storeModel) {
        return new MapMainPresenterImpl(context,mapMainView, storeModel);
    }

    @Provides
    StoreModel provideStoreModel(MenuApi api) {
        return new StoreModelImpl(api);
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
