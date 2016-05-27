package com.liuguilin.only;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.liuguilin.only.fragment.BlogFragment;
import com.liuguilin.only.fragment.GithubFragment;
import com.liuguilin.only.fragment.MoreFragment;
import com.liuguilin.only.fragment.NewsFragment;
import com.liuguilin.only.fragment.UserFragment;
import com.liuguilin.only.fragment.WechatFragment;
import com.liuguilin.only.view.CustomDialog;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    //城市标记
    public static final String KEY_PICKED_CITY = "picked_city";
    //ToolsBar
    private Toolbar toolbar;
    //FloatingActionButton
    private FloatingActionButton fab;
    //HeaderView
    private View headerView;
    //侧滑菜单
    private DrawerLayout drawer;
    //分享Dialog
    private CustomDialog dialog_share;
    //附近的人
    private FloatingActionButton action_a,action_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
        initPOP();
    }

    /**
     * 初始化PopWindows
     */
    private void initPOP() {

    }

    /**
     * 初始化控件
     */
    private void initView() {

        //跳转头条新闻
        initPagerContent(new NewsFragment());

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //头部
        headerView = navigationView.getHeaderView(0);
        headerView.findViewById(R.id.iv_circle).setOnClickListener(this);
        headerView.findViewById(R.id.tv_github).setOnClickListener(this);

        action_b = (FloatingActionButton) findViewById(R.id.action_b);
        action_b.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this,"PopWindows",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_toutiao) {
            //跳转头条新闻
            initPagerContent(new NewsFragment());
        } else if (id == R.id.nav_wechat) {
            //跳转微信精选
            initPagerContent(new WechatFragment());
        } else if (id == R.id.nav_blog) {
            //跳转我的博客
            initPagerContent(new BlogFragment());
        } else if (id == R.id.nav_more) {
            //跳转更多精彩
            initPagerContent(new MoreFragment());
        } else if (id == R.id.nav_share) {
            //跳转分享
            showShareDialog();
        } else if (id == R.id.nav_setting) {
            //跳转设置
            startActivity(new Intent(this, SettingActivity.class));
        }
        //关闭侧滑动画
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 分享Dialog
     */
    private void showShareDialog() {
        dialog_share = new CustomDialog(this,0,0,R.layout.dialog_share,R.style.Theme_dialog, Gravity.BOTTOM,R.style.pop_anim_style);
        dialog_share.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //跳转到我的信息
            case R.id.iv_circle:
                initPagerContent(new UserFragment());
                //关闭侧滑动画
                drawer.closeDrawer(GravityCompat.START);
                break;
            //跳转到我的Github
            case R.id.tv_github:
                //关闭侧滑动画
                drawer.closeDrawer(GravityCompat.START);
                initPagerContent(new GithubFragment());
                break;
            //附近的人
            case R.id.action_b:
                startActivity(new Intent(this,MapActivity.class));
                break;
        }

    }

    /**
     * 加载fragment
     *
     * @param fragment
     */
    private void initPagerContent(android.app.Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        //会话
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.myContent, fragment);
        ft.commit();
    }

}
