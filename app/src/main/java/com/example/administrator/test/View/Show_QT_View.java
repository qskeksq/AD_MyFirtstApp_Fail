package com.example.administrator.test.View;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.test.Presenter.Fragment.UpdateFragment.Update_QT_Fragment;
import com.example.administrator.test.R;

/**
 * Created by Administrator on 2017-06-13.
 */

public class Show_QT_View implements View.OnClickListener, View.OnLongClickListener {

    View view;
    Update_QT_Fragment fragment;

    Toolbar toolbar;
    public TextView date;
    public EditText txt_detail_week, txt_detail_qt, txt_detail_thanks, txt_detail_prayer, txt_detail_journal;
    FloatingActionButton fab;
    public CheckBox check_week, check_qt, check_thanks, check_prayer, check_journal;


    public Show_QT_View(View view, Update_QT_Fragment fragment){
        this.view = view;
        this.fragment = fragment;
        init();
    }

//--------------------------------------------------------------------------------------------------
//    1. 초기 작업
//--------------------------------------------------------------------------------------------------

    public void init(){
        findAddress();
        setToolbar(); // 순서 조심
        setListener();
        setClipBoardDisable();
        setEditDisabled();
    }

    public void findAddress(){
        toolbar = (Toolbar) view.findViewById(R.id.toolbar_update_qt);
        txt_detail_week = (EditText) view.findViewById(R.id.show_txt_detail_week);
        txt_detail_qt = (EditText) view.findViewById(R.id.show_txt_detail_qt);
        txt_detail_thanks = (EditText) view.findViewById(R.id.show_txt_detail_thanks);
        txt_detail_prayer = (EditText) view.findViewById(R.id.show_txt_detail_prayer);
        txt_detail_journal = (EditText) view.findViewById(R.id.show_txt_detail_journal);
        date = (TextView) view.findViewById(R.id.show_txt_detail_date);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        check_week = (CheckBox) view.findViewById(R.id.show_check_week);
        check_qt = (CheckBox) view.findViewById(R.id.show_check_qt);
        check_thanks =(CheckBox) view.findViewById(R.id.show_check_thanks);
        check_prayer = (CheckBox) view.findViewById(R.id.show_check_prayer);
        check_journal = (CheckBox) view.findViewById(R.id.show_check_journal);

    }

    public void setListener(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.onPause();
            }
        });
        fab.setOnClickListener(this);
        fab.setOnLongClickListener(this);
        txt_detail_week.addTextChangedListener(weekListener);
        txt_detail_qt.addTextChangedListener(QTWatcher);
        txt_detail_thanks.addTextChangedListener(thanksWatcher);
        txt_detail_prayer.addTextChangedListener(prayerWatcher);
        txt_detail_journal.addTextChangedListener(journalWatcher);
    }

    public void setToolbar(){
        ((AppCompatActivity)fragment.getActivity()).setSupportActionBar(toolbar);
        fragment.setHasOptionsMenu(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
    }

//--------------------------------------------------------------------------------------------------
//    2. 리스너
//--------------------------------------------------------------------------------------------------

    // 2.1      -- 콜백 클릭 메소드
    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.fab:
                fragment.copy();
                setClipBoardDisable();
                break;
        }
    }

    // 2.2      -- 콜백 롱클릭 메소드
    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                setClipBoardDisable();
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
            fragment.qt.setWeek(s.toString());
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
            fragment.qt.setQt(s.toString());
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
            fragment.qt.setThanks(s.toString());
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
            fragment.qt.setPrayer(s.toString());
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
            fragment.qt.setJournal(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

//--------------------------------------------------------------------------------------------------
//    3. 값 설정 메소드
//--------------------------------------------------------------------------------------------------

    // 3.1 편집 불가 메소드
    public void setEditDisabled(){
        txt_detail_week.setEnabled(false);
        txt_detail_qt.setEnabled(false);
        txt_detail_thanks.setEnabled(false);
        txt_detail_prayer.setEnabled(false);
        txt_detail_journal.setEnabled(false);
    }

    // 3.2 편집 가능 메소드
    public void setEditable(){
        txt_detail_week.setEnabled(true);
        txt_detail_qt.setEnabled(true);
        txt_detail_thanks.setEnabled(true);
        txt_detail_prayer.setEnabled(true);
        txt_detail_journal.setEnabled(true);
    }

    // 3.3 클립보드 사용
    public void setClipBoardEnabled(){

        check_week.setVisibility(View.VISIBLE);
        check_qt.setVisibility(View.VISIBLE);
        check_thanks.setVisibility(View.VISIBLE);
        check_prayer.setVisibility(View.VISIBLE);
        check_journal.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);

    }

    // 3.4 클립보드 완료 -- 복사 or 취소
    public void setClipBoardDisable(){
        check_week.setVisibility(View.GONE);
        check_qt.setVisibility(View.GONE);
        check_thanks.setVisibility(View.GONE);
        check_prayer.setVisibility(View.GONE);
        check_journal.setVisibility(View.GONE);
        fab.setVisibility(View.GONE);

    }

    // 3.5 아이콘 바꾸기
    boolean status = true;
    public void changeEditableIcon(MenuItem item){
        switch (item.getItemId()){
            case R.id.temp_save:
                if(status == true) {
                    item.setIcon(R.drawable.ic_action_read);
                    status = false;
                } else {
                    item.setIcon(R.drawable.ic_action_edit);
                    status = true;
                }
                break;
        }
    }

    // 3.6 삭제 여부 묻기
    public void askDelete(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(fragment.getContext());
        dialog.setTitle("삭제하시겠습니까");
        dialog.setNegativeButton("아니오", null);
        dialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fragment.delete();
            }
        });
        dialog.show();
    }

}
