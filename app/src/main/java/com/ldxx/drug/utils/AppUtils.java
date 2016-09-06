package com.ldxx.drug.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import javax.inject.Inject;


/**
 * Created by liaodongxiaoxiao
 * on 2016/8/25.
 */

public class AppUtils {
    private static final String TAG = "AppUtils";
    private Context context;

    @Inject
    public AppUtils(Context context) {
        this.context = context;
        Log.e(TAG, "AppUtils: " );
    }

    /**
     * 获取版本名称
     *
     * @return 版本号
     */
    public String getVersionName() {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    /**
     * 获取包名
     *
     * @return 返回包名
     */
    public String getPackageName() {
        return context.getPackageName();
    }

    public SharedPreferences getDefaultSharedPreferences() {
        return getSharedPreferences(getPackageName());
    }

    public SharedPreferences getSharedPreferences(String name) {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor getDefaultEditor() {
        return getDefaultSharedPreferences().edit();
    }
}
