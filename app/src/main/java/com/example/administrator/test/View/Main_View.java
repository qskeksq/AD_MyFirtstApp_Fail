package com.example.administrator.test.View;

import android.content.Context;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.test.Interface.Main_Interface;
import com.example.administrator.test.Interface.Main_View_Interface;
import com.example.administrator.test.Presenter.Activity.MainActivity;
import com.example.administrator.test.R;

public class Main_View implements Main_View_Interface,View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle toggle;
    public NavigationView navigationView;
    public ImageView btn_navi_settings, img_navi_profile;
    ImageView photo, camera;

    View headerlayout;
    Main_Interface activity;

    public Main_View(Main_Interface main, Context context){
        activity = main;
        init();
    }

    // 0. init
    public void init(){
//        activity = new MainActivity();
        findAddress();
        setListener();
        set_header_disabled();
//        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        toggle.syncState();
    }


//--------------------------------------------------------------------------------------------------
//    1. findViewById
//--------------------------------------------------------------------------------------------------

    public void findAddress(){
        drawerLayout = (DrawerLayout) ((MainActivity)activity).findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) ((MainActivity)activity).findViewById(R.id.nav_view);
        headerlayout = navigationView.getHeaderView(0); // 헤더 뷰 얻는 법!!!!!!! 매우 오랜 시간이 걸렸음. 잘 숙지할 것!!
        btn_navi_settings = (ImageView) headerlayout.findViewById(R.id.btn_navigation_settings);
        img_navi_profile = (ImageView) headerlayout.findViewById(R.id.imageView);
        photo = (ImageView) ((MainActivity)activity).dialogView.findViewById(R.id.img_nav_photo);
        camera = (ImageView) ((MainActivity)activity).dialogView.findViewById(R.id.img_nav_camera);
    }

//--------------------------------------------------------------------------------------------------
//    2. 리스너 --  메인에서 누르는 것은 다 여기서 처리한다
//--------------------------------------------------------------------------------------------------

    public void setListener(){
        drawerLayout.setDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(this);
        btn_navi_settings.setOnClickListener(this);
        img_navi_profile.setOnClickListener(this);
        photo.setOnClickListener(this);
        camera.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_navigation_settings:
                activity.manage_nav_settings(v);
                break;
            case R.id.imageView:
                activity.manage_profileDialog(activity.getDialog());
                break;
            case R.id.img_nav_photo:
                activity.runPhoto();
                break;
            case R.id.img_nav_camera:
                activity.runCamera();
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_add_qt:
                activity.show_qtFragment();
                break;
            case R.id.menu_add_words:
                activity.show_wordsFragment();
                break;
            case R.id.menu_add_decision:
                activity.show_decisionFragment();
                break;
            case R.id.menu_list:
                activity.show_listFragment();
                break;
            case R.id.menu_calendar:
                activity.show_calendarFragment();
                break;
            case R.id.menu_settings:
                activity.show_settingsFragment();
//                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
//                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container, new Settings_Fragment()).commit();
//                TODO 스택 처리 안 해주면 계속 쌓인다
//                int index = getSupportFragmentManager().getBackStackEntryCount() -1 ;
//                Log.e("Main", index+"");
//                String tag = getSupportFragmentManager().getBackStackEntryAt(index).getName();
//                Log.e("Main", tag);
//                fragment = getSupportFragmentManager().findFragmentByTag(tag);
//                Log.e("Main", "확인1");
//                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
//                Log.e("Main", "확인2");

//                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container, new Settings_Fragment()).commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START); // 선택된 후에는 drawer 을 닫는다.
        return true;
    }

//--------------------------------------------------------------------------------------------------
//    3. set
//--------------------------------------------------------------------------------------------------

    // 헤더 편집 가능
    @Override
    public void set_header_enabled() {
        img_navi_profile.setEnabled(true);
        btn_navi_settings.setImageResource(R.drawable.ic_action_save3);
    }

    // 헤더 편집 불가
    @Override
    public void set_header_disabled() {
        img_navi_profile.setEnabled(false);
        btn_navi_settings.setImageResource(R.drawable.ic_action_settings_for_navigation);
    }

    // 헤더 아이콘 이미지 바꾸기
    @Override
    public void set_nav_settingsIcon_changed() {

    }

    @Override
    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    @Override
    public void setImageUri(Uri imageUri){

        img_navi_profile.setImageURI(imageUri);
    }



}
