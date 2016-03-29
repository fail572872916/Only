package com.liuguilin.only;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * 地图页
 * Created by LGL on 2016/3/25.
 */
public class MapActivity extends BaseActivity implements View.OnClickListener{

    //路况，导航，周边
    private TextView tvRoad,tvNavigation,tvSurrounding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        initView();

    }

    private void initView() {
        tvSurrounding = (TextView) findViewById(R.id.tvSurrounding);
        tvSurrounding.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvSurrounding:
                startActivity(new Intent(this,SurroundingActivity.class));
                break;
        }
    }
}
