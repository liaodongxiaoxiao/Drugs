package com.ldxx.drug.model;

import com.ldxx.drug.base.IBaseRequestCallBack;
import com.ldxx.drug.model.bean.Store;

import java.util.List;

/**
 * Created by wangzhuo-neu
 * on 2016/9/26.
 */

public interface StoreModel {
    void getAroundStores(int page, double x, double y,
                                IBaseRequestCallBack<List<Store>> callBack);
}
