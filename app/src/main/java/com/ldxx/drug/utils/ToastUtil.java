package com.ldxx.drug.utils;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

/**
 * Toast 工具类
 */
public class ToastUtil {

    private Context mContext;

    @Inject
    public ToastUtil(Context context) {
        this.mContext = context;
    }

    public void showToast(String message) {
        showToast(message, Toast.LENGTH_LONG);
    }

    public void showToast(String message, int time) {
        Toast.makeText(mContext, message, time).show();
    }

}
