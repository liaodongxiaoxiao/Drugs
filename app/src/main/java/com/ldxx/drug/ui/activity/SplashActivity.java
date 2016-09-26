package com.ldxx.drug.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.ldxx.drug.R;
import com.ldxx.drug.base.BaseActivity;
import com.ldxx.drug.component.AppComponent;
import com.ldxx.drug.component.DaggerSplashComponent;
import com.ldxx.drug.module.SplashModule;
import com.ldxx.drug.utils.AppUtils;
import com.ldxx.drug.utils.Constant;
import com.ldxx.drug.utils.ToastUtil;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity {

    @Inject
    AppUtils appUtils;

    @Inject
    ToastUtil toastUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        checkFirst();

        toMain();
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        DaggerSplashComponent.builder()
                .appComponent(appComponent)
                .splashModule(new SplashModule())
                .build()
                .inject(this);
    }

    private void checkFirst() {
        SharedPreferences share = appUtils.getDefaultSharedPreferences();
        boolean isFirst = share.getBoolean(Constant.IS_FIRST, true);
        if (isFirst) {
            toastUtil.showToast("第一次来");
            SharedPreferences.Editor editor = appUtils.getDefaultEditor();
            editor.putBoolean(Constant.IS_FIRST, false);
            editor.commit();
        }

    }

    private void toMain() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MapMainActivity.class));
                finish();
            }
        }, 2000);
    }
}
