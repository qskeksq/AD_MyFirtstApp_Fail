package com.example.administrator.test.View;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.test.Presenter.Fragment.UpdateFragment.Update_Words_Fragment;
import com.example.administrator.test.R;


public class Show_Words_View {

    Toolbar toolbar;
    View view;
    Update_Words_Fragment fragment;

    public TextView txt_words_title, txt_words_book, txt_words_text, txt_words_summary;

    public Show_Words_View(View view, Update_Words_Fragment fragment){

        this.view = view;
        this.fragment = fragment;
        init();

    }

    // 0. init()
    public void init(){
        findAddress();
        setListener();
        setToolbar();
        setDisabled();
    }

    // 1. findViewById
    public void findAddress(){
        toolbar = (Toolbar) view.findViewById(R.id.toolbar_update_words);
        txt_words_title = (TextView) view.findViewById(R.id.show_txt_words_title);
        txt_words_book = (TextView) view.findViewById(R.id.show_txt_words_book);
        txt_words_text = (TextView) view.findViewById(R.id.show_txt_words_text);
        txt_words_summary = (TextView) view.findViewById(R.id.show_txt_words_summary);
    }

    // 2. 리스너
    public void setListener(){
        // 이렇게 하자. 리스너 세팅은 여기서 하고 실제 리스너는 메소드로 프레젠터에 다 뺴준다.

    }

    // 3. 초기화 함수
    public void setToolbar(){
        ((AppCompatActivity)fragment.getActivity()).setSupportActionBar(toolbar);
        fragment.setHasOptionsMenu(true);  // 당연히 fragment 소속일 수밖에 없다.
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 이거 어떻게 밖으로 뺴내냐
                fragment.onPause();
            }
        });
    }

    public void setDisabled(){
        txt_words_title.setEnabled(false);
        txt_words_book.setEnabled(false);
        txt_words_text.setEnabled(false);
        txt_words_summary.setEnabled(false);
    }

    public void setEnabled(){
        txt_words_title.setEnabled(true);
        txt_words_book.setEnabled(true);
        txt_words_text.setEnabled(true);
        txt_words_summary.setEnabled(true);
    }

    public void askDelete(){
        AlertDialog.Builder askSave = new AlertDialog.Builder(fragment.getContext());
        askSave.setTitle("삭제하시겠습니까?");
        askSave.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fragment.delete();
            }
        });
        askSave.setNegativeButton("아니오", null);
        askSave.show();
    }



}
