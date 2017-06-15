package com.example.administrator.test.Interface;

import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Administrator on 2017-06-14.
 */

public interface QT_View_Inferface {

    void init();
    void findAddress();                            // findViewById
    void setListener();                            // 리스너

    void setToolbar();                             // 툴바 설정
    void setEditable();
    void setEditDisabled();
    void setClipboardEnabled();
    void setClipboardDisabled();
    void setThanksVisible();
    void setThanksInvisible();
    void setPrayerVisible();
    void setPrayerInvisible();

    void loadWeekData_toView(String week);          // 프레젠터로부터 데이터를 받아와 값을 세팅해주는 메소드
    void loadQTData_toView(String data);             //
    void loadPrayerData_toView(String prayer);       //
    void loadThanksData_toView(String thanks);       //
    void loadJournalData_toView(String journal);     //
    void loadDate_toView(String data);

    TextView getWeekTxt();
    TextView getQtTxt();
    TextView getThanksTxt();
    TextView getPrayerTxt();
    TextView getJournalTxt();

    CheckBox getWeekCheck();
    CheckBox getQtCheck();
    CheckBox getThanksCheck();
    CheckBox getPrayerCheck();
    CheckBox getJournalCheck();






}
