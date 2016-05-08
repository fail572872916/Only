package com.liuguilin.only.application;

import android.app.Application;
import android.graphics.Typeface;

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
    }

    /**
     * 初始化
     */
    private void initView() {
        fontFace = Typeface.createFromAsset(getAssets(),
                "fonts/YYG.TTF");
    }
}
