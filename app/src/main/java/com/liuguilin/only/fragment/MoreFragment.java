package com.liuguilin.only.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguilin.only.R;

/**
 * 更多精彩
 * Created by LGL on 2016/5/4.
 */
public class MoreFragment extends Fragment {

    private GridView mGridView;

    private int[] mDraw = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private String[] mText = {"归属地查询", "身份证查询", "QQ测吉凶", "五子棋游戏", "美女拼图"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, null);
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
        mGridView.setAdapter(new GridViewAdapter(getActivity()));
    }


    public class GridViewAdapter extends BaseAdapter {

        private LayoutInflater mInflater;
        private Context mContext;

        public GridViewAdapter(Context mContext) {
            this.mContext = mContext;
            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mDraw.length;
        }

        @Override
        public Object getItem(int position) {
            return mDraw[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.grid_item, null);
                viewHolder.mImageView = (ImageView) convertView.findViewById(R.id.mImageView);
                viewHolder.mTextView = (TextView) convertView.findViewById(R.id.mTextView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.mImageView.setImageResource(mDraw[position]);
            viewHolder.mTextView.setText(mText[position]);
            return convertView;
        }
    }

    class ViewHolder {
        private ImageView mImageView;
        private TextView mTextView;
    }
}
