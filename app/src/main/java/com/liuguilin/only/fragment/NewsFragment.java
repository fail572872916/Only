package com.liuguilin.only.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.liuguilin.only.R;
import com.liuguilin.only.utils.L;
import com.liuguilin.only.utils.PicassoUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 今日头条
 * Created by LGL on 2016/5/4.
 */
public class NewsFragment extends Fragment {

    //轮播图接口
    private String url = "http://v3.wufazhuce.com:8000/api/reading/carousel";

    private ViewPager mViewPager;

    private List<View> mList = new ArrayList<>();

    //存放图片地址
    private List<String> mListImgUrl = new ArrayList<>();
    //存放标题
    private List<String> mListTitle = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        findView(view);
        return view;
    }

    /**
     * 初始化
     *
     * @param view
     */
    private void findView(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.mViewPager);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                L.i("json" + s);
                Get_Json(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
    }

    /**
     * 解析json
     */
    private void Get_Json(String json) {
        try {
            JSONObject jsonObject1 = new JSONObject(json);
            JSONArray jsonArray1 = jsonObject1.getJSONArray("data");

            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject2 = (JSONObject) jsonArray1.get(i);
                mListImgUrl.add(jsonObject2.getString("cover"));
                mListTitle.add(jsonObject2.getString("bottom_text"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
