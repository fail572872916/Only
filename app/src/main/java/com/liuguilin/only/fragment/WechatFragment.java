package com.liuguilin.only.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuguilin.only.R;

/**
 * 微信精选
 * Created by LGL on 2016/5/4.
 */
public class WechatFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wechat,null);
        findView(view);
        return view;
    }

    /**
     * 初始化
     * @param view
     */
    private void findView(View view) {
    }
}
