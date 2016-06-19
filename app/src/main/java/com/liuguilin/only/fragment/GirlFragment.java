package com.liuguilin.only.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.liuguilin.only.R;
import com.liuguilin.only.adapter.GridAdapter;
import com.liuguilin.only.bean.GirlBean;
import com.liuguilin.only.utils.L;
import com.liuguilin.only.view.CustomDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 福利
 * Created by LGL on 2016/5/4.
 */
public class GirlFragment extends Fragment {


    //Gank的接口
    private String url = "http://gank.io/api/search/query/listview/category/福利/count/50/page/1 ";

    //列表
    private GridView mGridView;

    private List<GirlBean> mList = new ArrayList<>();

    private GridAdapter adapter;

    //存储url
    private List<String> urlList = new ArrayList<>();

    //提示框
    private CustomDialog imgDialog;

    private ImageView iv_girl_pow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_girl, null);
        findView(view);
        return view;
    }

    /**
     * 初始化
     *
     * @param view
     */
    private void findView(View view) {
        mGridView = (GridView) view.findViewById(R.id.mGridView);

        imgDialog = new CustomDialog(getActivity(), LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, R.layout.dialog_girl, R.style.Theme_dialog, Gravity.CENTER, R.style.pop_anim_style);
        iv_girl_pow = (ImageView) imgDialog.findViewById(R.id.iv_girl_pow);

        RequestQueue queue = Volley.newRequestQueue(getActivity());
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
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);

                GirlBean bean = new GirlBean();
                bean.setTime(jsonObject1.getString("publishedAt"));
                bean.setUrl(jsonObject1.getString("url"));
                mList.add(bean);

                urlList.add(jsonObject1.getString("url"));
            }

            adapter = new GridAdapter(getActivity(), mList);
            mGridView.setAdapter(adapter);

            //点击事件
            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    L.i(urlList.get(position));
//                    Glide.with(getActivity()).load(urlList.get(position)).into(iv_girl_pow);
                    Volley_Iv(urlList.get(position));
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    // 加载图片
    protected void Volley_Iv(String imgUrl) {
        //请求
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        ImageRequest imageRequest = new ImageRequest(imgUrl, new Response.Listener<Bitmap>() {

            @Override
            public void onResponse(Bitmap response) {
                //成功就直接设置获取到的bitmap图片
                iv_girl_pow.setImageBitmap(response);
                imgDialog.show();
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // 失败了
            }
        });
        //最后将这个ImageRequest对象添加到RequestQueue里就可以
        queue.add(imageRequest);
    }
}
