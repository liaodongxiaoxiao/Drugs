package com.ldxx.drug.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.ldxx.drug.model.StoreModel;
import com.ldxx.drug.presenter.MapMainPresenter;
import com.ldxx.drug.ui.view.MapMainView;

import java.util.List;

/**
 * Created by wangzhuo-neu
 * on 2016/9/26.
 */

public class MapMainPresenterImpl implements MapMainPresenter {
    private static final String TAG = "MapMainPresenterImpl";

    private AMapLocationClient locationClient;

    private MapMainView mapMainView;
    private StoreModel storeModel;
    private Context context;

    public MapMainPresenterImpl(Context context, MapMainView mapMainView, StoreModel storeModel) {
        this.mapMainView = mapMainView;
        this.storeModel = storeModel;
        this.context = context;
    }

    @Override
    public void initLocation() {
        locationClient = new AMapLocationClient(context);
        //初始化定位参数
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        locationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //定位成功回调信息，设置相关消息
                        amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表

                        LatLng latLng = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
                        mapMainView.setCurrentPosition(latLng);
                        getAroundStores(latLng);
                        locationClient.stopLocation();
                        /*
                        amapLocation.getAccuracy();//获取精度信息
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(amapLocation.getTime());
                        df.format(date);//定位时间*/
                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
            }
        });
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //设置定位参数
        locationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        locationClient.startLocation();
    }

    private void getAroundStores(LatLng latLng) {
        final RegeocodeQuery rq = new RegeocodeQuery(new LatLonPoint(latLng.latitude, latLng.longitude)
                , 10, "autonavi");
        final GeocodeSearch gs = new GeocodeSearch(context);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RegeocodeAddress address = gs.getFromLocation(rq);
                    Log.e(TAG, "RegeocodeAddress: " + address.getCity() + " " + address.getCityCode());
                    final PoiSearch.Query query = new PoiSearch.Query("药房|药店", null, address.getCityCode());
                    final PoiSearch poiSearch = new PoiSearch(context, query);
                    poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
                        @Override
                        public void onPoiSearched(PoiResult result, int i) {
                            Log.e(TAG, "onPoiSearched: " + i + " " + result.getPois().size());
                            if (i == 1000) {
                                // 取得搜索到的poiitems有多少页
                                List<PoiItem> poiItems = result.getPois();

                                if (poiItems != null && poiItems.size() > 0) {
                                    mapMainView.setDrugStore(poiItems);
                                } else {
                                    Log.e(TAG, "onPoiSearched: no poi item");
                                }
                            } else {
                                Log.e(TAG, "onPoiSearched: no data ");
                            }
                        }

                        @Override
                        public void onPoiItemSearched(PoiItem poiItem, int i) {
                            Log.e(TAG, "onPoiItemSearched: ");
                        }
                    });
                    poiSearch.searchPOIAsyn();
                } catch (AMapException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        /**/
        /*storeModel.getAroundStores(page, latLng.longitude, latLng.latitude, new IBaseRequestCallBack<List<Store>>() {
            @Override
            public void requestError(Throwable e) {
                Log.e(TAG, "requestError: " + e.getMessage());
            }

            @Override
            public void requestComplete() {
                Log.e(TAG, "requestComplete: ");
            }

            @Override
            public void requestSuccess(List<Store> stores) {
                mapMainView.setDrugStore(stores);
            }
        });*/
    }
}
