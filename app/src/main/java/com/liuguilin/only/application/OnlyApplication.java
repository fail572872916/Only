package com.liuguilin.only.application;

import android.app.Application;
import android.graphics.Typeface;

import com.baidu.mapapi.SDKInitializer;

/**
 * Application
 * Created by LGL on 2016/5/8.
 */
public class OnlyApplication extends Application {

    //字体
    private Typeface fontFace;

    @Override
    public void onCreate() {
        initView();
        super.onCreate();
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
    }

    /**
     * 初始化
     */
    private void initView() {
        fontFace = Typeface.createFromAsset(getAssets(),
                "fonts/YYG.TTF");
    }
}
