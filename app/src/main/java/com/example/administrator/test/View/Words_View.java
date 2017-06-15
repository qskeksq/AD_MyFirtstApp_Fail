package com.example.administrator.test.View;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.test.Interface.Main_Interface;
import com.example.administrator.test.Interface.Words_Fragment_Interface;
import com.example.administrator.test.Interface.Words_View_Interface;
import com.example.administrator.test.Presenter.Activity.MainActivity;
import com.example.administrator.test.Presenter.Fragment.Words_Fragment;
import com.example.administrator.test.R;

public class Words_View implements Words_View_Interface {

    Main_Interface mi;
    Toolbar toolbar;
    View view;
    Words_Fragment_Interface fragment;
    Activity activity;

    public TextView txt_words_title, txt_words_book, txt_words_text, txt_words_summary;

    public Words_View(View view, Words_Fragment fragment, Activity activity){
        this.view = view;
        this.fragment = fragment;
        this.activity = activity;
        mi = (MainActivity) activity;
        init();

    }

    // 0. init()
    public void init(){
//        mi = new MainActivity();
//        fragment = new Words_Fragment();
//        view = fragment.returnView();
//        activity = fragment.returnActivity();
        findAddress();
        setListener();
        setToolbar();
    }

//--------------------------------------------------------------------------------------------------
//    1. findViewById
//--------------------------------------------------------------------------------------------------

    public void findAddress(){
        toolbar = (Toolbar) view.findViewById(R.id.toolbar2);
        txt_words_title = (TextView) view.findViewById(R.id.show_txt_words_title);
        txt_words_book = (TextView) view.findViewById(R.id.show_txt_words_book);
        txt_words_text = (TextView) view.findViewById(R.id.txt_words_text);
        txt_words_summary = (TextView) view.findViewById(R.id.show_txt_words_summary);
    }

//--------------------------------------------------------------------------------------------------
//    2. 리스너
//--------------------------------------------------------------------------------------------------

    public void setListener(){
        // 이렇게 하자. 리스너 세팅은 여기서 하고 실제 리스너는 메소드로 프레젠터에 다 뺴준다.

        txt_words_title.addTextChangedListener(titleWatcher);
        txt_words_book.addTextChangedListener(bookWatcher);
        txt_words_text.addTextChangedListener(textWatcher);
        txt_words_summary.addTextChangedListener(summaryWatcher);

    }

    TextWatcher titleWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            fragment.get_TitleData_fromView(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    TextWatcher bookWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            fragment.get_BookData_fromView(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            fragment.get_TextData_fromView(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    TextWatcher summaryWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            fragment.get_SummaryData_fromView(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

//--------------------------------------------------------------------------------------------------
//    3. 초기화 함수
//--------------------------------------------------------------------------------------------------

    public void setToolbar(){
        ((AppCompatActivity)activity).setSupportActionBar(toolbar);
        fragment.returnFragment().setHasOptionsMenu(true);  // 당연히 fragment 소속일 수밖에 없다.
        toolbar.setNavigationIcon(R.drawable.ic_action_hamburgur);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 이거 어떻게 밖으로 뺴내냐
                mi.openDrawer();
            }
        });
    }

//--------------------------------------------------------------------------------------------------
//    인터페이스
//--------------------------------------------------------------------------------------------------

    // 데이터 뿌려주기

    @Override
    public void loadTitleData_toView(String title) {
        txt_words_title.setText(title);
    }

    @Override
    public void loadBookData_toView(String book) {
        txt_words_book.setText(book);
    }

    @Override
    public void loadTextData_toView(String text) {
        txt_words_text.setText(text);
    }

    @Override
    public void loadSummaryData_toView(String summary) {
        txt_words_summary.setText(summary);
    }

    // 텍스트뷰 리턴

//    @Override
//    public TextView getTitleTxt() {
//        return txt_words_title;
//    }
//
//    @Override
//    public TextView getBookTxt() {
//        return txt_words_book;
//    }
//
//    @Override
//    public TextView getTextTxt() {
//        return txt_words_text;
//    }
//
//    @Override
//    public TextView getSummaryTxt() {
//        return txt_words_summary;
//    }
}
