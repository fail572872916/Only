package com.liuguilin.only;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * 返回基类
 * Created by LGL on 2016/3/25.
 */
public class BaseActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
