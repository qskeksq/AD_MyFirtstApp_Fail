package com.example.administrator.test.View;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.test.Interface.Main_Interface;
import com.example.administrator.test.Interface.QT_Fragment_Interface;
import com.example.administrator.test.Interface.QT_View_Inferface;
import com.example.administrator.test.Presenter.Activity.MainActivity;
import com.example.administrator.test.R;


public class QT_View implements View.OnClickListener, View.OnLongClickListener, QT_View_Inferface{

    Main_Interface di;
    QT_Fragment_Interface fragment;

    View view;
    Toolbar toolbar;
    TextView date;
    FloatingActionButton fab;
    ImageView btn_add_thanks, btn_add_prayer;
    public EditText txt_detail_week, txt_detail_qt, txt_detail_thanks, txt_detail_prayer, txt_detail_journal;
    public CheckBox check_week, check_qt, check_thanks, check_prayer, check_journal;
    Activity activity;

    public QT_View(View view, QT_Fragment_Interface presenter){
        this.fragment = fragment;
        this.view = view;
        this.activity = activity;
        di = (MainActivity) activity;
        init();

    }

    public void init(){
//        di = new MainActivity(); // 새로 생성하면 안되고 있던 것을 get 해와야 하는구나
//        fragment = new QT_Fragment();
//        view = fragment.returnView();
//        activity = fragment.returnContext();
        findAddress();
        set(); // 툴바 이거 순서 안 지키면 리스너 안됨.
        setListener();

    }

//--------------------------------------------------------------------------------------------------
//    1. findViewById
//--------------------------------------------------------------------------------------------------

    public void findAddress(){
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        date = (TextView) view.findViewById(R.id.show_txt_detail_date);
        txt_detail_week = (EditText) view.findViewById(R.id.show_txt_detail_week);
        txt_detail_qt = (EditText) view.findViewById(R.id.show_txt_detail_qt);
        txt_detail_thanks = (EditText) view.findViewById(R.id.show_txt_detail_thanks);
        txt_detail_prayer = (EditText) view.findViewById(R.id.show_txt_detail_prayer);
        txt_detail_journal = (EditText) view.findViewById(R.id.txt_detail_journal);
        btn_add_thanks = (ImageView) view.findViewById(R.id.btn_add_thanks);
        btn_add_prayer = (ImageView) view.findViewById(R.id.btn_add_prayer);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        check_week = (CheckBox) view.findViewById(R.id.show_check_week);
        check_qt = (CheckBox) view.findViewById(R.id.show_check_qt);
        check_thanks =(CheckBox) view.findViewById(R.id.show_check_thanks);
        check_prayer = (CheckBox) view.findViewById(R.id.show_check_prayer);
        check_journal = (CheckBox) view.findViewById(R.id.check_journal);
    }

//--------------------------------------------------------------------------------------------------
//    2. 리스너
//--------------------------------------------------------------------------------------------------

    public void setListener(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // TODO 이거 어떻게 밖으로 뺴냐
            @Override
            public void onClick(View v) {
                di.openDrawer();
            }
        });

        btn_add_thanks.setOnClickListener(this);     // 감사 추가 버튼 리스너
        btn_add_prayer.setOnClickListener(this);     // 기도 추가 버튼 리스너
        fab.setOnClickListener(this);                // 클립보드로 복사 리스너
        fab.setOnLongClickListener(this);            // 클립보드 복사 취소 리스너
        txt_detail_week.addTextChangedListener(weekListener);
        txt_detail_qt.addTextChangedListener(QTWatcher);
        txt_detail_thanks.addTextChangedListener(thanksWatcher);
        txt_detail_prayer.addTextChangedListener(prayerWatcher);
        txt_detail_journal.addTextChangedListener(journalWatcher);
    }

    // 2.1      -- 콜백 클릭 메소드
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.btn_add_thanks:
//                fragment.addThanks();
                break;
            case R.id.btn_add_prayer:
//                fragment.addPrayer();
                break;
            case R.id.fab:
                fragment.copy();
                setClipboardDisabled();
                break;
        }
    }

    // 2.2      -- 콜백 롱클릭 메소드
    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                setClipboardDisabled();
        }
        return false;
    }

    // 2.3      -- 콜백 텍스트 변화 메소드
    private TextWatcher weekListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            fragment.get_WeekData_fromView(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher QTWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            fragment.get_QTData_fromView(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher thanksWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            fragment.get_ThanksData_fromView(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher prayerWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            fragment.get_PrayerData_fromView(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher journalWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            fragment.get_JournalData_fromView(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

//--------------------------------------------------------------------------------------------------
//    3. set
//--------------------------------------------------------------------------------------------------

    // 3.1 초기화
    public void set() {
        setClipboardDisabled();
        setThanksInvisible();
        setPrayerInvisible();
        setToolbar();
    }

    // 3. 값 설정
    public void setToolbar(){
        ((AppCompatActivity)activity).setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_hamburgur);

    }

    // 3.2 편집 불가 메소드
    public void setEditDisabled(){
        txt_detail_week.setEnabled(false);
        txt_detail_qt.setEnabled(false);
        txt_detail_thanks.setEnabled(false);
        txt_detail_prayer.setEnabled(false);
        txt_detail_journal.setEnabled(false);
        btn_add_thanks.setEnabled(false);
        btn_add_prayer.setEnabled(false);
    }

    // 3.2 편집 가능 메소드
    public void setEditable(){
        txt_detail_week.setEnabled(true);
        txt_detail_qt.setEnabled(true);
        txt_detail_thanks.setEnabled(true);
        btn_add_prayer.setEnabled(true);
        txt_detail_journal.setEnabled(true);
        btn_add_thanks.setEnabled(true);
        btn_add_prayer.setEnabled(true);
    }


    // 3.3 클립보드 사용
    @Override
    public void setClipboardEnabled(){

        check_week.setVisibility(View.VISIBLE);
        check_qt.setVisibility(View.VISIBLE);
        check_thanks.setVisibility(View.VISIBLE);
        check_prayer.setVisibility(View.VISIBLE);
        check_journal.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);

    }

    // 3.4 클립보드 완료 -- 복사 or 취소
    @Override
    public void setClipboardDisabled(){
        check_week.setVisibility(View.GONE);
        check_qt.setVisibility(View.GONE);
        check_thanks.setVisibility(View.GONE);
        check_prayer.setVisibility(View.GONE);
        check_journal.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);

    }

    // 3.5 감사 추가 보이기 & 안보이기
    public void setThanksInvisible(){
        txt_detail_thanks.setVisibility(View.GONE);
    }

    public void setThanksVisible(){
        txt_detail_prayer.setVisibility(View.VISIBLE);
    }

    // 3.6 기도 추가 보이기 & 안보이기
    public void setPrayerInvisible(){
        txt_detail_prayer.setVisibility(View.GONE);
    }

    public void setPrayerVisible(){
        txt_detail_prayer.setVisibility(View.VISIBLE);
    }

//--------------------------------------------------------------------------------------------------
//    데이터 뿌려주기 : (P -- > v)
//--------------------------------------------------------------------------------------------------

    @Override
    public void loadWeekData_toView(String week) {
        txt_detail_week.setText(week);
    }

    @Override
    public void loadQTData_toView(String data) {
        txt_detail_qt.setText(data);
    }

    @Override
    public void loadPrayerData_toView(String prayer) {
        txt_detail_prayer.setText(prayer);
    }

    @Override
    public void loadThanksData_toView(String thanks) {
        txt_detail_thanks.setText(thanks);
    }

    @Override
    public void loadJournalData_toView(String journal) {
        txt_detail_journal.setText(journal);
    }

    @Override
    public void loadDate_toView(String data){
        date.setText(data);
    }

//--------------------------------------------------------------------------------------------------
//    인터페이스 통신을 위한 리턴
//--------------------------------------------------------------------------------------------------

    @Override
    public TextView getWeekTxt() {
        return txt_detail_week;
    }

    @Override
    public TextView getQtTxt() {
        return txt_detail_qt;
    }

    @Override
    public TextView getThanksTxt() {
        return txt_detail_thanks;
    }

    @Override
    public TextView getPrayerTxt() {
        return txt_detail_prayer;
    }

    @Override
    public TextView getJournalTxt() {
        return txt_detail_journal;
    }

    @Override
    public CheckBox getWeekCheck() {
        return check_week;
    }

    @Override
    public CheckBox getQtCheck() {
        return check_qt;
    }

    @Override
    public CheckBox getThanksCheck() {
        return check_thanks;
    }

    @Override
    public CheckBox getPrayerCheck() {
        return check_prayer;
    }

    @Override
    public CheckBox getJournalCheck() {
        return check_journal;
    }




}
