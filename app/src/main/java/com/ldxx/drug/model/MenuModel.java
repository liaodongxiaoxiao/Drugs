package com.ldxx.drug.model;

import com.ldxx.drug.base.IBaseRequestCallBack;
import com.ldxx.drug.model.bean.MenuDetail;
import com.ldxx.drug.model.bean.MenuList;

import java.util.List;

/**
 * Created by liaodongxiaoxiao
 * on 2016/8/31.
 */

public interface MenuModel {
    void getMenus(int page, IBaseRequestCallBack<List<MenuList>> callBack);

    void getDetails(String id, IBaseRequestCallBack<MenuDetail> callBack);
}
