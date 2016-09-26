package com.ldxx.drug.model.impl;

import com.ldxx.drug.api.MenuApi;
import com.ldxx.drug.base.IBaseRequestCallBack;
import com.ldxx.drug.model.StoreModel;
import com.ldxx.drug.model.bean.ResultBean;
import com.ldxx.drug.model.bean.Store;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangzhuo-neu
 * on 2016/9/26.
 */

public class StoreModelImpl implements StoreModel {
    private MenuApi api;

    @Inject
    public StoreModelImpl(MenuApi api) {
        this.api = api;
    }

    @Override
    public void getAroundStores(int page, double x, double y, final IBaseRequestCallBack<List<Store>> callBack) {
        api.getAroundStores(page, x, y).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResultBean<List<Store>>>() {
                    @Override
                    public void onCompleted() {
                        callBack.requestComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.requestError(e);
                    }

                    @Override
                    public void onNext(ResultBean<List<Store>> bean) {
                        if (!bean.getStatus()) {
                            callBack.requestError(new Exception("null"));
                        } else {
                            callBack.requestSuccess(bean.getTngou());
                        }
                    }
                });
    }
}
