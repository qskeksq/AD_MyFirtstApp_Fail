package com.example.administrator.test.Interface;

import android.net.Uri;
import android.support.v4.widget.DrawerLayout;

/**
 * Created by Administrator on 2017-06-14.
 */

public interface Main_View_Interface {

    void init();                             // 초기화 메소드
    void findAddress();                      // findViewById 모하놓은 메소드
    void setListener();                      // 각종 리스너 세팅 메소드
//    void set();                              // 초기에 설정되어야 하는 값 모음

    void set_header_enabled();              // 헤더 사용 가능
    void set_header_disabled();              // 헤더 사용 불가
    void set_nav_settingsIcon_changed();     // 헤더 설정 아이콘 바꾸기 메소드

    DrawerLayout getDrawerLayout();
    void setImageUri(Uri imageUri);




}
