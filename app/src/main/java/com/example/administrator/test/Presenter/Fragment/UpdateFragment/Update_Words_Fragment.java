package com.example.administrator.test.Presenter.Fragment.UpdateFragment;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.test.Model.Words;
import com.example.administrator.test.Model.Words_Lab;
import com.example.administrator.test.Presenter.Fragment.ListFragment.List_Fragment;
import com.example.administrator.test.R;
import com.example.administrator.test.Schema;
import com.example.administrator.test.View.Show_Words_View;

/**
 * A simple {@link Fragment} subclass.
 */
public class Update_Words_Fragment extends Fragment {

    private static final String ID_KEY = "id_key2";
    Show_Words_View words_view;
    View view;
    Words words;

    // 아이디 값을 받아온다.
    public static Update_Words_Fragment newInstance(int id){
        Update_Words_Fragment fragment = new Update_Words_Fragment();
        Bundle args = new Bundle();
        args.putInt(ID_KEY, id);
        fragment.setArguments(args);
        return fragment;
    }

    // 아이디 값을 얻어온다.
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int id = getArguments().getInt(ID_KEY);
        words = Words_Lab.getWordsLAB(getContext()).getData(id);

    }

    // 핵심
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_words_update, container, false);
        init();
        return view;
    }

    public void init(){
        words_view = new Show_Words_View(view, this);
        out();
    }

    // 옵션 인플레이션
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate(R.menu.menu_show_words, menu);
    }

    // 옵션 관리
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.show_words_edit:
                update();
                break;
            case R.id.show_words_copy:
                copy();
                break;
            case R.id.show_words_delete:
                words_view.askDelete();
                break;
        }
        return true;
    }


    // 업데이트
    boolean status = true;
    public void update(){

        if(status){
            words_view.setEnabled();
            status = false;
            Toast.makeText(getContext(), "편집", Toast.LENGTH_SHORT).show();
        } else {
            words_view.setDisabled();
            status = true;

            // QT 와 방식이 다른 것은 QT 에서는 textWatcher 로 실시간으로 데이터를 업데이트 했다면 여기서는 저장을 누를 때 업데이트 해서 DB 에 보내준다
            words.setTitle(words_view.txt_words_title.getText().toString());
            words.setBook(words_view.txt_words_book.getText().toString());
            words.setWords(words_view.txt_words_text.getText().toString());
            words.setSummary(words_view.txt_words_summary.getText().toString());

            Words_Lab.getWordsLAB(getContext()).updateWords(words);

            Toast.makeText(getContext(), "업데이트 되었습니다", Toast.LENGTH_SHORT).show();

        }
    }

    // 삭제
    public void delete(){
        // 어차피 저장된 것이 없기 때문에 임시 저장된 것만 지워주면 된다.

        Words_Lab.getWordsLAB(getContext()).deleteWords(words);
        onPause();
    }

    // 공유 TODO 카카오톡이나 메일로 보낼 수 있도록 암시적 인텐트 만든다.
    public void copy(){
        String clipped =
                "- Title" + "\n" + words_view.txt_words_title.getText().toString() + "\n"
                        + "- Book" + "\n" + words_view.txt_words_book.getText().toString() + "\n"
                        + "- Words" + "\n" + words_view.txt_words_text.getText().toString() + "\n"
                        + "- Summary" + "\n" + words_view.txt_words_summary.getText().toString();
        if(!clipped.equals("")) {
            ClipData clip = ClipData.newPlainText(Schema.Words_Fragment_S.KEY_COPY, clipped);
            ClipboardManager manager = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            manager.setPrimaryClip(clip);
            Toast.makeText(getContext(), "복사되었습니다.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "클립보드가 비었습니다.", Toast.LENGTH_SHORT).show();
        }


    }


    // 프래그먼트가 멈출 때 자동으로 임시 저장된다.
    public void onPause(){
        super.onPause();
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new List_Fragment()).commit();

    }

    public void out(){

        words_view.txt_words_title.setText(words.getTitle());
        words_view.txt_words_book.setText(words.getBook());
        words_view.txt_words_text.setText(words.getWords());
        words_view.txt_words_summary.setText(words.getSummary());

    }




}
