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

import com.example.administrator.test.Model.QT;
import com.example.administrator.test.Model.QT_Lab;
import com.example.administrator.test.Presenter.Fragment.ListFragment.List_Fragment;
import com.example.administrator.test.R;
import com.example.administrator.test.View.Show_QT_View;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Update_QT_Fragment extends Fragment {

    private static final String ID_KEY = "id_key";
    View view;
    public QT qt;
    Show_QT_View qt_view;

    // 아이디 값을 가진 프래그먼트를 반환
    public static Update_QT_Fragment newInstance(int id){
        Update_QT_Fragment fragment = new Update_QT_Fragment();
        Bundle args = new Bundle();
        args.putInt(ID_KEY, id);
        fragment.setArguments(args);
        return fragment;
    }

    // 아이디 값을 얻어오기
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int id = getArguments().getInt(ID_KEY);
        qt = QT_Lab.getQTLAB(getContext()).getData(id); // 이 id 값을 가진 qt 객체를 불러오는 것이다.

    }

    // 핵심
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_qt_update, container, false);
        init();

        return view;
    }

    public void init(){
        qt_view = new Show_QT_View(view, this);
        out();
    }

    // 스택에서 지우기
    public void onPause(){
        super.onPause();
        // 매우 깊고도 깊게 공부해야 할 사항이다. 일단 스택에 대한 이해가 전혀 되지 않았고, 계속해서 프래그먼트 생성하는 방식으로 하는 것 좋지 않다.
        // 스스로를 스택에서 지워버림 -- 이렇게 함으로써 드로워로 나가더라도 스택을 0, 1, 2 로 계속 유지할 수 있다.
//        int backStackIndex = getActivity().getSupportFragmentManager().getBackStackEntryCount()-1;
//        String backStackName = getActivity().getSupportFragmentManager().getBackStackEntryAt(backStackIndex).getName();
//        getActivity().getSupportFragmentManager().findFragmentByTag(backStackName).onResume();
//        Toast.makeText(getContext(), backStackIndex+""+backStackName, Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new List_Fragment()).commit();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_show_qt, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.show_qt_edit:
                edit();
                qt_view.changeEditableIcon(item); // TODO 이것도 edit 안으로 들어가야 함
                break;
            case R.id.show_qt_copy:
                qt_view.setClipBoardEnabled();  //
                break;
            case R.id.show_qt_delete:
                qt_view.askDelete();
                break;
        }
        return true;
    }

//--------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------

    // 수정
    boolean status = true;
    public void edit(){
        if(status){
            qt_view.setEditable();
            status = false;
            Toast.makeText(getContext(), "쓰기", Toast.LENGTH_SHORT).show();
        } else {
            update();
            qt_view.setEditDisabled();
            status = true;
            Toast.makeText(getContext(), "업데이트", Toast.LENGTH_SHORT).show();
        }
    }

    // 업데이트
    public void update(){
        QT_Lab.getQTLAB(getContext()).updateQT(qt);
    }

    // 삭제
    public void delete(){

        QT_Lab.getQTLAB(getContext()).deleteQT(qt);
        onPause();
    }

    // 클립보드에 복사
    public void copy(){
        String clipped = "";    // TODO 왜 public 으로 해 줘야 하는거냐
        if(qt_view.check_week.isChecked()){
            clipped = clipped + "- 이번 주 말씀 \n" + qt_view.txt_detail_week.getText().toString() + "\n";
        }
        if(qt_view.check_qt.isChecked()){
            clipped = clipped + "- 오늘 말씀 \n" + qt_view.txt_detail_qt.getText().toString() +"\n";
        }
        if(qt_view.check_thanks.isChecked()){
            clipped = clipped + "- 감사 \n" + qt_view.txt_detail_thanks.getText().toString() + "\n";
        }
        if(qt_view.check_prayer.isChecked()){
            clipped = clipped + "- 기도 \n" + qt_view.txt_detail_prayer.getText().toString() + "\n";
        }
        if(qt_view.check_journal.isChecked()){
            clipped = clipped + "- 일기 \n" + qt_view.txt_detail_journal.getText().toString();
        }

        if(!clipped.equals("")){
            ClipData clip = ClipData.newPlainText("text", clipped);
            ClipboardManager cm = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            cm.setPrimaryClip(clip);
            Toast.makeText(getContext(), "복사되었습니다.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "클립보드가 비었습니다", Toast.LENGTH_SHORT).show();
        }

    }

    // 화면에 보여질 데이터 뷰에 뿌려주기
    public void out(){
        qt_view.txt_detail_week.setText(qt.getWeek());
        qt_view.txt_detail_qt.setText(qt.getQt());
        qt_view.txt_detail_thanks.setText(qt.getThanks());
        qt_view.txt_detail_prayer.setText(qt.getPrayer());
        qt_view.txt_detail_journal.setText(qt.getJournal());
        qt_view.date.setText(sdf(qt.getDate()));
    }

    public String sdf(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String result = sdf.format(date);
        return result;
    }

}
