package com.liuguilin.only.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Volley工具类
 * Created by LGL on 2016/6/3.
 */
public class VolleyUtils {

    private static String json = "";

    /**
     * 解析Json
     * @param mContext 上下文
     * @param url 链接
     * @return 返回数据
     */
    public static String Volley_Get_Json(Context mContext, String url) {

        RequestQueue queue = Volley.newRequestQueue(mContext);
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    // 成功
                    @Override
                    public void onResponse(String text) {
                        // Log.i("json", json);
                        json = text;
                    }
                }, new Response.ErrorListener() {
            // 失败
            @Override
            public void onErrorResponse(VolleyError errorLog) {
            }
        });
        queue.add(request);
        L.i("json",json);
        return json;
    }
}
