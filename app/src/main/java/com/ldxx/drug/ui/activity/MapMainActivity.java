package com.ldxx.drug.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.overlay.PoiOverlay;
import com.amap.api.services.core.PoiItem;
import com.ldxx.drug.R;
import com.ldxx.drug.base.BaseActivity;
import com.ldxx.drug.component.AppComponent;
import com.ldxx.drug.component.DaggerMapMainComponent;
import com.ldxx.drug.module.MapMainModule;
import com.ldxx.drug.presenter.MapMainPresenter;
import com.ldxx.drug.ui.view.MapMainView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapMainActivity extends BaseActivity implements MapMainView {
    private static final String TAG = "MapMainActivity";

    @BindView(R.id.map)
    MapView mapView;

    private AMap map;

    @Inject
    MapMainPresenter presenter;

    private LatLng location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_main);
        ButterKnife.bind(this);
        mapView.onCreate(savedInstanceState);
        map = mapView.getMap();
        UiSettings settings =map.getUiSettings();
        settings.setZoomControlsEnabled(false);

        presenter.initLocation();
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerMapMainComponent.builder()
                .appComponent(appComponent)
                .mapMainModule(new MapMainModule(this, this))
                .build().inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void setCurrentPosition(LatLng latLng) {
        location = latLng;
        map.addMarker(new MarkerOptions().
                position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.location)));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18f));
    }

    @Override
    public void setDrugStore(List<PoiItem> stores) {
        Log.e(TAG, "setDrugStore: " + stores.size());
        //map.clear();// 清理之前的图标
        PoiOverlay poiOverlay = new PoiOverlay(map, stores);
        poiOverlay.removeFromMap();
        poiOverlay.addToMap();
        poiOverlay.zoomToSpan();
        map.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //map.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(),15.4f));
                return false;
            }
        });
        //map.clear();
        /*LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(location);
        LatLng latlng;
        for (Store store : stores) {
            latlng = new LatLng(store.getY(), store.getX());
            map.addMarker(new MarkerOptions().position(latlng)
                    .title(store.getName()));
            builder.include(latlng);
            Log.e(TAG, "setDrugStore: " + store.getX() + " " + store.getY());

        }

        map.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 20));*/

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
