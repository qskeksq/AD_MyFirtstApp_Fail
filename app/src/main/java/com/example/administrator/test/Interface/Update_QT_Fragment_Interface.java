package com.example.administrator.test.Interface;

import com.example.administrator.test.Presenter.Fragment.UpdateFragment.Update_QT_Fragment;

/**
 * Created by Administrator on 2017-06-14.
 */

public interface Update_QT_Fragment_Interface {

    Update_QT_Fragment newInstance(int id); // 객체 반환
    void init();                            // 초기화
    void edit();                            // 편집
    void update();                          // 업데이트
    void delete();                          // 삭제
    void deleteDialog();
    void copy();                            // 클립보드 복사
    void changeEditableIcon();              // 아이콘 바꾸기

    void get_WeekData_fromView(String title);       // 뷰로부터 얻어온 말씀 데이터를 받아오는 메소드
    void get_QTData_fromView(String qt);            // 뷰로부터 얻어온 큐티 데이터를 받아오는 메소드
    void get_PrayerData_fromView(String prayer);    // 뷰로부터 얻어온 기도 데이터를 받아오는 메소드
    void get_ThanksData_fromView(String thanks);    // 뷰로부터 얻어온 감사 데이터를 받아오는 메소드
    void get_JournalData_fromView(String journal);  // 뷰로부터 얻어온 일기 데이터를 받아오는 메소드



}
