package com.yf.istudy.application;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2016/11/22.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
