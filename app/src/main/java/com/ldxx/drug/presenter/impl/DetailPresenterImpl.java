package com.ldxx.drug.presenter.impl;


import com.ldxx.drug.base.IBaseRequestCallBack;
import com.ldxx.drug.model.MenuModel;
import com.ldxx.drug.model.bean.MenuDetail;
import com.ldxx.drug.presenter.DetailPresenter;
import com.ldxx.drug.ui.view.DetailView;

/**
 * Created by liaodongxiaoxiao
 * on 2016/9/5.
 */

public class DetailPresenterImpl implements DetailPresenter {

    private static final String TAG = "MainPresenterImpl";
    private DetailView detailView;
    private MenuModel menuListModel;

    public DetailPresenterImpl(DetailView detailView, MenuModel menuListModel) {
        this.detailView = detailView;
        this.menuListModel = menuListModel;
    }

    @Override
    public void getDetail(String id) {
        menuListModel.getDetails(id, new IBaseRequestCallBack<MenuDetail>() {
            @Override
            public void requestError(Throwable e) {

            }

            @Override
            public void requestComplete() {

            }

            @Override
            public void requestSuccess(MenuDetail callBack) {
                detailView.setDetail(callBack);
                detailView.hideProgress();
            }
        });
    }
}
