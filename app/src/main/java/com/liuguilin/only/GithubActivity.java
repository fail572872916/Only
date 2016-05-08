package com.liuguilin.only;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.liuguilin.only.adapter.GithubAdapter;
import com.liuguilin.only.bean.GithubBean;
import com.liuguilin.only.view.ExplosionField;

import java.util.ArrayList;
import java.util.List;

/**
 * Github
 * Created by LGL on 2016/5/8.
 */
public class GithubActivity extends BaseActivity {

    //列表
    private ListView mListView;
    //Adapter
    private GithubAdapter adapter;

    //数据
    private String[] name_data = {"Only", "Coding-Developer-Book"};
    private String[] web_data = {"https://github.com/LiuGuiLinAndroid/Only", "https://github.com/LiuGuiLinAndroid/Coding-Developer-Book"};

    //实体类
    private GithubBean bean;
    //装载数据
    private List<GithubBean>mList = new ArrayList<GithubBean>();
    // 实例化粒子动画
    private ExplosionField explosionField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github);

        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {

        explosionField = new ExplosionField(this);
        // 绑定哪个控件哪个控件就有效果，如果需要整个layout，只要绑定根布局的id即可
        explosionField.addListener(findViewById(R.id.iv_circle));

        mListView = (ListView) findViewById(R.id.mListView);
        for (int i = 0; i < name_data.length ; i ++){
            bean = new GithubBean();
            bean.setName(name_data[i]);
            bean.setWebAddress(web_data[i]);
            mList.add(bean);
        }
        adapter = new GithubAdapter(this,mList);
        mListView.setAdapter(adapter);

        //点击事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri = Uri.parse(web_data[position]);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
    }
}
