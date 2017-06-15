package com.example.administrator.test.Interface;

/**
 * Created by Administrator on 2017-06-14.
 */

public interface Update_Qt_View_Interface {

    void init();
    void findAddress();                            // findViewById
    void setListener();                            // 리스너

    void setToolbar();                             // 툴바 설정
    void setEditable();
    void setEditDisabled();
    void setClipboardabled();
    void setClipboardDisabled();

    void loadWeekData_toView(String title);          // 프레젠터로부터 데이터를 받아와 값을 세팅해주는 메소드
    void loadQTData_toView(String data);             //
    void loadPrayerData_toView(String prayer);       //
    void loadThanksData_toView(String thanks);       //
    void loadJournalData_toView(String journal);     //

}
