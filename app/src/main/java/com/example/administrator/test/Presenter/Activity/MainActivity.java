package com.example.administrator.test.Presenter.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.test.BuildConfig;
import com.example.administrator.test.Interface.Main_Interface;
import com.example.administrator.test.Interface.Main_View_Interface;
import com.example.administrator.test.Presenter.Fragment.Calendar_Fragment;
import com.example.administrator.test.Presenter.Fragment.Decision_Fragment;
import com.example.administrator.test.Presenter.Fragment.ListFragment.List_Fragment;
import com.example.administrator.test.Presenter.Fragment.QT_Fragment;
import com.example.administrator.test.Presenter.Fragment.Settings_Fragment;
import com.example.administrator.test.Presenter.Fragment.Words_Fragment;
import com.example.administrator.test.R;
import com.example.administrator.test.View.Main_View;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements  Main_Interface {

    QT_Fragment qt_fragment; Words_Fragment words_fragment; Decision_Fragment decision_fragment;
    List_Fragment list_fragment; Calendar_Fragment calendar_fragment; Settings_Fragment settings_fragment;
//    Main_View view; 아하!! 인터페이스를 이렇게 사용하는구나!
    Main_View_Interface view;
    public View dialogView;
    AlertDialog.Builder dialog;
    boolean status = true;
    boolean back_status = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_main);

        init();
        show_qtFragment();

    }

    // 초기화
    @Override
    public void init(){
        // init 에 넣어서 초기화 할 때 조심할 것. Main View 가 먼저 생성되고 그 다음 다이얼로그 뷰가 인플레이션 된다면 MAin_VIew 에서 참조할 것이 없어진다.
        dialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null);
        view = new Main_View(this); // 아하!! 인터페이스를 이렇게 사용하는구나, 어차피 어디선가는 객체를 생성해 줘야 함
        qt_fragment = new QT_Fragment();
        words_fragment = new Words_Fragment();
        decision_fragment = new Decision_Fragment();
        list_fragment = new List_Fragment();
        calendar_fragment = new Calendar_Fragment();
        settings_fragment = new Settings_Fragment();
    }

    // back 키 메소드
    @Override
    public void onBackPressed() {
        if(back_status) {
            if (view.getDrawerLayout().isDrawerOpen(GravityCompat.START)) {
                view.getDrawerLayout().closeDrawer(GravityCompat.START);
            } else {
                back_status = false;
                Toast.makeText(this, "한번 더 누르면 앱이 종료됩니다", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        back_status = true;
                    }
                }, 3000);
            }
        } else {
            finish();
        }
    }

//--------------------------------------------------------------------------------------------------
//    프래그먼트 관리 메소드
//--------------------------------------------------------------------------------------------------

    // 프래그먼트 띄우기 메소드
    @Override
    public void show_qtFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, qt_fragment).commit();
    }

    @Override
    public void show_wordsFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, words_fragment).commit();
    }

    @Override
    public void show_decisionFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, decision_fragment).commit();
    }

    @Override
    public void show_listFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new List_Fragment()).commit();
    }

    @Override
    public void show_calendarFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, calendar_fragment).commit();
    }

    @Override
    public void show_settingsFragment() {
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, settings_fragment).commit();
    }

//--------------------------------------------------------------------------------------------------
//    Header 영역 관리
//--------------------------------------------------------------------------------------------------

    // 헤더 설정(Settings) 관리
    @Override
    public void manage_nav_settings(View v) {
        if(status == true) {
            manage_popup(v);
            status = false;
        } else {
            view.set_header_disabled();
            status = true;
        }
    }

    // 헤더 설정 -- 팝업메뉴
    @Override
    public void manage_popup(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.popup_edit:
                        view.set_header_enabled();
                }
                return true;
            }
        });
        popupMenu.show();
    }

    // 헤더 프로필 대화상자
    @Override
    public void manage_profileDialog(View dialogView) {
        dialog = new AlertDialog.Builder(this);
        dialog.setView(dialogView);
        // 퍼미션 체크를 먼저 하고 그 다음 다이얼로그를 띄우면 굳이 제어문을 쓰지 않아도 된다.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            customCheckPermission();
        } else {
            dialog.show();
        }
    }

//--------------------------------------------------------------------------------------------------
//    Drawer 열고 닫기
//--------------------------------------------------------------------------------------------------

    // 인터페이스를 통해서 drawer 을 여는 메소드 오버라이딩
    @Override
    public void openDrawer() {

        ((Main_View)view).drawerLayout.openDrawer(GravityCompat.START);
    }

//--------------------------------------------------------------------------------------------------
//    인터페이스를 통해 -- 자원 넘겨주기
//--------------------------------------------------------------------------------------------------

    @Override
    public Activity getMainContext(){
        return this;

    }

    @Override
    public View getDialog(){
        return dialogView;
    }


//--------------------------------------------------------------------------------------------------
//    권한 설정 영역
//--------------------------------------------------------------------------------------------------

    private static final int REQ_CODE = 99;
    private static final int REQ_PHOTO_CODE = 100;
    private static final int REQ_CAM_CODE = 101;

    // 갤러리 암시적 인텐트
    @Override
    public void runPhoto(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "선택"), REQ_PHOTO_CODE);
    }

    // 카메라 암시적 인텐트
    Uri uri = null;
    @Override
    public void runCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photoFile = createFile();
        if(photoFile != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                uri = FileProvider.getUriForFile(getBaseContext(), BuildConfig.APPLICATION_ID + ".provider", createFile());
            } else {
                uri = Uri.fromFile(createFile());
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, REQ_CAM_CODE);
        }
    }

    // 카메라 저장을 위한 file 과 저장소 만들기
    public File createFile(){
        // 저장소 만들기
        String tempFileName = "TEMP_"+System.currentTimeMillis();
        File dir = new File(Environment.getExternalStorageDirectory() +"/CameraN3/");
        if(!dir.exists()){
            dir.mkdir();
        }

        File file = null;
        try {
            file = File.createTempFile(tempFileName, ".jpg", dir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    // 권한 설정을 체크하는 메소드 -- 권한이 없으면 requestPermission 해준다.
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void customCheckPermission(){
        // 기존에 권한 설정이 되어 있다면 바로 실행
        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            dialog.show();
        } else {
            // 기존 권한 설정이 안 되어 있으면 권한을 묻는 메소드 호출
            String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
            requestPermissions(perms , REQ_CODE);
        }
    }

    // 시스템에서 호출해주는 권한 요청 비동기 메소드
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 만약 승인한다면 실행
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
            dialog.show();
        } else {
            Toast.makeText(this, "권한요청을 승인하셔야 앱을 사용할 수 있습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    // 요청을 했으면 값을 받아와야 한다.
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            if(requestCode == REQ_PHOTO_CODE){
                Uri imageUri = data.getData();
                view.setImageUri(imageUri); // TODO View 영역이지만 그냥 둠.
            }
        }
    }

}
