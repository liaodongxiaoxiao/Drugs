package com.ldxx.drug.ui.view;

import com.amap.api.maps2d.model.LatLng;
import com.amap.api.services.core.PoiItem;
import com.ldxx.drug.base.IBaseView;
import com.ldxx.drug.model.bean.Store;

import java.util.List;

/**
 * Created by wangzhuo-neu
 * on 2016/9/26.
 */

public interface MapMainView extends IBaseView {
    void setCurrentPosition(LatLng latLng);

    void setDrugStore(List<PoiItem> stores);
}
