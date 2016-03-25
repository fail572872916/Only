package com.liuguilin.only;

import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * 页面跳转网页
 * Created by LGL on 2016/3/25.
 */
public class WebViewActivity extends BaseActivity{

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_webview);
    }
}
