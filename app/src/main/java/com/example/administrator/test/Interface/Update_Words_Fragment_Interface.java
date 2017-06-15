package com.example.administrator.test.Interface;


public interface Update_Words_Fragment_Interface {

    void init();              // 기본 초기화
    void update();
    void delete();            // 삭제
    void deleteDialog();      // 삭제 여부를 묻는 대화상자
    void copy();              // 클립보드에 복사

    void get_TitleData_fromView(String title);        // 뷰로부터 얻어온 제목 데이터를 받아오는 메소드
    void get_BookData_fromView(String book);            // 뷰로부터 얻어온 말씀 데이터를 받아오는 메소드
    void get_TextData_fromView(String text);        // 뷰로부터 얻어온 설교 데이터를 받아오는 메소드
    void get_SummaryData_fromView(String summary);     // 뷰로부터 얻어온 요약 데이터를 받아오는 메소드


}
