package com.example.administrator.test.Interface;

import android.app.Activity;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Administrator on 2017-06-14.
 */

public interface QT_Fragment_Interface {


    void init();              // 기본 초기화
    void save();              // 저장
    void saveDialog();         // 저장 여부를 묻는 대화상자
    void delete();            // 삭제
    void deleteDialog();      // 삭제 여부를 묻는 대화상자
    void setTempSave();         // 임시 저장
    void loadTempSaved();
    void removeTempSaved();
    void copy();              // 클립보드에 복사
    void changeEditableIcon(MenuItem item);// 아이콘 바꾸기

    void get_WeekData_fromView(String title);       // 뷰로부터 얻어온 말씀 데이터를 받아오는 메소드
    void get_QTData_fromView(String qt);            // 뷰로부터 얻어온 큐티 데이터를 받아오는 메소드
    void get_PrayerData_fromView(String prayer);    // 뷰로부터 얻어온 기도 데이터를 받아오는 메소드
    void get_ThanksData_fromView(String thanks);    // 뷰로부터 얻어온 감사 데이터를 받아오는 메소드
    void get_JournalData_fromView(String journal);  // 뷰로부터 얻어온 일기 데이터를 받아오는 메소드

    View returnView();
    Activity returnContext();


}
