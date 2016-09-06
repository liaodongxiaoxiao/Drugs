package com.ldxx.drug.ui.view;

import com.ldxx.drug.base.IBaseView;
import com.ldxx.drug.model.bean.MenuList;

import java.util.List;

/**
 * Created by liaodongxiaoxiao on
 * 2016/8/31.
 */

public interface MainView extends IBaseView {
    
    void setItems(List<MenuList> items);
}
