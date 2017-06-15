package com.example.administrator.test.Presenter.Fragment;


import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.test.Interface.Words_Fragment_Interface;
import com.example.administrator.test.Interface.Words_View_Interface;
import com.example.administrator.test.Model.Words;
import com.example.administrator.test.Model.Words_Lab;
import com.example.administrator.test.R;
import com.example.administrator.test.Schema;
import com.example.administrator.test.View.Words_View;


public class Words_Fragment extends Fragment implements Words_Fragment_Interface {

    Words_View_Interface words_view;
    View view;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Words words;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_words, container, false);

        words_view = new Words_View(view, this, getActivity());
        init();

        return view;
    }

    public void init(){
        sp = getActivity().getSharedPreferences("words_key", Context.MODE_PRIVATE);
        words = new Words();

    }

//--------------------------------------------------------------------------------------------------
//    옵션 관리
//--------------------------------------------------------------------------------------------------

    // 옵션 인플레이션
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_words, menu);
    }

    // 옵션 관리
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.words_save:
                saveDialog();
                break;
            case R.id.words_delete:
                deleteDialog();
                break;
            case R.id.words_temp_save:
                tempSaved();
                Toast.makeText(getContext(), "임시로 저장되었습니다", Toast.LENGTH_SHORT).show();
                break;
            case R.id.words_copy:
                copy();
                break;
        }
        return true;
    }

//--------------------------------------------------------------------------------------------------
//    저장, 삭제, 임시저장, 로딩
//--------------------------------------------------------------------------------------------------

    // 저장 대화상자
    @Override
    public void saveDialog() {
        AlertDialog.Builder askSave = new AlertDialog.Builder(getContext());
        askSave.setTitle("저장하시겠습니까?");
        askSave.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                save();
            }
        });
        askSave.setNegativeButton("아니오", null);
        askSave.show();
    }

    // 저장
    @Override
    public void save(){

        Words_Lab.getWordsLAB(getContext()).addWords(words);

        // 임시 저장소 초기화
        words_view.loadTitleData_toView("");
        words_view.loadBookData_toView("");
        words_view.loadTextData_toView("");
        words_view.loadSummaryData_toView("");

        // 또한 새로운 프래그먼트를 띄우주는 이유는 새 QT 객체를 만들어 주는 것, 날짜 갱신

//        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Words_Fragment()).commit();
    }

    // 삭제 대화상자
    @Override
    public void deleteDialog() {
        AlertDialog.Builder askSave = new AlertDialog.Builder(getContext());
        askSave.setTitle("삭제하시겠습니까?");
        askSave.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delete();
                Toast.makeText(getContext(), "삭제되었습니다", Toast.LENGTH_SHORT).show();
            }
        });
        askSave.setNegativeButton("아니오", null);
        askSave.show();
    }

    // 삭제
    @Override
    public void delete(){
        // 어차피 저장된 것이 없기 때문에 임시 저장된 것만 지워주면 된다.
        words_view.loadTitleData_toView("");
        words_view.loadBookData_toView("");
        words_view.loadTextData_toView("");
        words_view.loadSummaryData_toView("");
//        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Words_Fragment()).commit();
    }

    // 임시 저장
    @Override
    public void tempSaved() {
        editor = sp.edit();
        editor.putString(Schema.Words_Fragment_S.KEY_TITLE, words.getTitle());
        editor.putString(Schema.Words_Fragment_S.KEY_BOOK, words.getBook());
        editor.putString(Schema.Words_Fragment_S.KEY_TEXT, words.getWords());
        editor.putString(Schema.Words_Fragment_S.KEY_SUMMARY, words.getSummary());
        editor.commit();
    }

    // 로딩되기
    public void load(){

        String title = sp.getString(Schema.Words_Fragment_S.KEY_TITLE, "");
        String book = sp.getString(Schema.Words_Fragment_S.KEY_BOOK, "");
        String text = sp.getString(Schema.Words_Fragment_S.KEY_TEXT, "");
        String summary = sp.getString(Schema.Words_Fragment_S.KEY_SUMMARY, "");

        words_view.loadTitleData_toView(title);
        words_view.loadBookData_toView(book);
        words_view.loadTextData_toView(text);
        words_view.loadSummaryData_toView(summary);

    }

    // 프래그먼트가 멈출 때 자동으로 임시 저장된다.
    public void onPause(){
        super.onPause();
        tempSaved();
    }

    // 프래그먼트가 resume 될 때 자동으로 로딩된다.
    public void onResume(){
        super.onResume();
        load();
    }

    // 클립보드에 복사
    public void copy(){
        String clipped =
                "- Title" + "\n" + words.getTitle() + "\n"
                        + "- Book" + "\n" + words.getBook() + "\n"
                        + "- Words" + "\n" + words.getWords() + "\n"
                        + "- Summary" + "\n" + words.getSummary();
        if(!clipped.equals("")) {
            ClipData clip = ClipData.newPlainText(Schema.Words_Fragment_S.KEY_COPY, clipped);
            ClipboardManager manager = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            manager.setPrimaryClip(clip);
            Toast.makeText(getContext(), "복사되었습니다.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "클립보드가 비었습니다.", Toast.LENGTH_SHORT).show();
        }


    }

//--------------------------------------------------------------------------------------------------
//    데이터 받아오기
//--------------------------------------------------------------------------------------------------

    @Override
    public void get_TitleData_fromView(String title) {
        words.setTitle(title);
    }

    @Override
    public void get_BookData_fromView(String book) {
        words.setBook(book);
    }

    @Override
    public void get_TextData_fromView(String text) {
        words.setWords(text);
    }

    @Override
    public void get_SummaryData_fromView(String summary) {
        words.setSummary(summary);
    }

//--------------------------------------------------------------------------------------------------
//    인터페이스
//--------------------------------------------------------------------------------------------------
    @Override
    public View returnView() {
        return view;
    }

    @Override
    public Activity returnActivity() {
        return getActivity();
    }

    @Override
    public Fragment returnFragment() {
        return this;
    }
}
