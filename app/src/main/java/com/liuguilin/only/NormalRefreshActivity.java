package com.liuguilin.only;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.liuguilin.only.adapter.GridAdapter;
import com.liuguilin.only.bean.GirlBean;
import com.liuguilin.only.utils.L;
import com.ybao.pullrefreshview.layout.BaseFooterView;
import com.ybao.pullrefreshview.layout.BaseHeaderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ybao on 2015/11/3 0003.
 */
public class NormalRefreshActivity extends AppCompatActivity implements BaseHeaderView.OnRefreshListener, BaseFooterView.OnLoadListener {

    ListView listView;
    BaseHeaderView headerView;
    BaseFooterView footerView;

    ArrayAdapter adapter;

    List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_refresh);

        listView = (ListView) findViewById(R.id.list);
        headerView = (BaseHeaderView) findViewById(R.id.header);
        footerView = (BaseFooterView) findViewById(R.id.footer);
        getGirl();
        list = getData(15);

        adapter = new ArrayAdapter(this, R.layout.item, list);

        listView.setAdapter(adapter);

        headerView.setOnRefreshListener(this);
        footerView.setOnLoadListener(this);
    }

    @Override
    public void onRefresh(BaseHeaderView baseHeaderView) {
        baseHeaderView.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                List<String> datas = getData(5);
                list.clear();
                list.addAll(datas);
                adapter.notifyDataSetChanged();
                headerView.stopRefresh();
            }
        }, 3000);
    }

    @Override
    public void onLoad(BaseFooterView baseFooterView) {
        baseFooterView.postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                List<String> datas = getData(5);
                getGirl();
                list.addAll(datas);
                adapter.notifyDataSetChanged();
                footerView.stopLoad();
            }
        }, 3000);
    }


    int page = 1;

    private List<String> getData(int n) {
        List<String> datas = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            datas.add("第" + page + "页,第" + i + "条");
        }
        return datas;
    }
    /**
     * 解析国内新闻数据
     */
    private void getGirl(){
        //聚合的接口
//        String url = "http://gank.io/api/search/query/listview/category/福利/count/50/page/" + count;
      String url=  "http://api.avatardata.cn/GuoNeiNews/Query?key=166a772bb5954abab5ed6cc179939ca3&page="+page+"&rows=10";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    // 成功
                    @Override
                    public void onResponse(String json) {
//                        L.i("json", json);
                        Volley_news(json);
                    }
                }, new Response.ErrorListener() {
            // 失败
            @Override
            public void onErrorResponse(VolleyError errorLog) {
            }
        });

        queue.add(request);
    }
    /**
     * json解析
     *
     * @param json
     */
    private void Volley_news(String json) {

        try {
            JSONObject jsonObject = new JSONObject(json);

            Log.d("NormalRefreshActivity", json);

//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
//
//
//            }




        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

