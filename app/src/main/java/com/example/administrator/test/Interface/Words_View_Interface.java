package com.example.administrator.test.Interface;


public interface Words_View_Interface {

    void init();
    void findAddress();                            // findViewById
    void setListener();                            // 리스너
    void setToolbar();                             // 툴바 설정

    void loadTitleData_toView(String title);          // 프레젠터로부터 데이터를 받아와 값을 세팅해주는 메소드
    void loadBookData_toView(String book);            //
    void loadTextData_toView(String text);            //
    void loadSummaryData_toView(String summary);      //


//    TextView getTitleTxt();
//    TextView getBookTxt();
//    TextView getTextTxt();
//    TextView getSummaryTxt();


}
