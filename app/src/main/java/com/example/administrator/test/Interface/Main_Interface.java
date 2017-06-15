package com.example.administrator.test.Interface;

import android.app.Activity;
import android.view.View;

import java.io.File;

/**
 * Created by Administrator on 2017-06-14.
 */

public interface Main_Interface {

    void init();                    // 전체 관리, 초기화 메소드
    void show_qtFragment( );        // 큐티 프래그먼트 띄우는 메소드
    void show_wordsFragment( );     // 말씀 프래그먼트 띄우는 메소드
    void show_decisionFragment( );  // 기도 프래그먼트 띄우는 메소드
    void show_listFragment( );      // 목록 프래그먼트 띄우는 메소드
    void show_calendarFragment( );  // 캘린더 프래그먼트 띄우는 메소드
    void show_settingsFragment( );  // 설정 프래그먼트 띄우는 메소드
    void openDrawer();              // 프래그먼트에서 Drawer 를 수동으로 열 수 있도록 함

    void manage_nav_settings(View view);     // Drawer 의 설정 버튼 제어 메소드
    void manage_popup(View view);            // Drawer 의 설정 버튼 누를 때에 조건에 따라 팝업 버튼을 띄우는 메소드
    void manage_profileDialog(View view);    // 프로필 사진을 누르면 대화상자를 띄우는 메소드
    void runPhoto();                         // 갤러리 인텐트 띄우는 메소드
    void runCamera();                        // 카메라 인텐트 띄우는 메소드
    void customCheckPermission();            // 퍼미션 띄우는 메소드
    File createFile();                       // 카메라 저장을 위해 파일을 만듦

//    void onBackPressed();
//    void closeDrawer();

    Activity getMainContext();      //V 와 P 의 연결을 끊기 위한 메소드
    View getDialog();               //  그런데 이렇게까지 해야 하는가?

}
