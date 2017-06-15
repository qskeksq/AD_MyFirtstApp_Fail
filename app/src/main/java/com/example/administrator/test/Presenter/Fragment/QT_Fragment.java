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

import com.example.administrator.test.Interface.QT_Fragment_Interface;
import com.example.administrator.test.Interface.QT_View_Inferface;
import com.example.administrator.test.Model.QT;
import com.example.administrator.test.Model.QT_Lab;
import com.example.administrator.test.R;
import com.example.administrator.test.View.QT_View;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.administrator.test.R.id.save;
import static com.example.administrator.test.Schema.QT_Fragment_S.JOURNAL;
import static com.example.administrator.test.Schema.QT_Fragment_S.QT;
import static com.example.administrator.test.Schema.QT_Fragment_S.PRAYER;
import static com.example.administrator.test.Schema.QT_Fragment_S.TEMP_SAVE_KEY;
import static com.example.administrator.test.Schema.QT_Fragment_S.THANKS;
import static com.example.administrator.test.Schema.QT_Fragment_S.WEEK;


public class QT_Fragment extends Fragment implements QT_Fragment_Interface {

    SharedPreferences sp;
    QT_View_Inferface qt_view;
    View view;
    public QT qt;
    boolean status = false;
    boolean iconStatus = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_qt, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
    }

    public void init(){
        qt = new QT();
        if(qt_view != null)
            qt_view = new QT_View(view);

        setHasOptionsMenu(true);  // 으아아아아아아아아아아아아ㅏ아아아아아아아아아아아악 이거 한 줄 때문에 10 시간 날림
        sp = getActivity().getSharedPreferences(TEMP_SAVE_KEY, Context.MODE_PRIVATE);
        loadTempSaved();
        out();
    }

//--------------------------------------------------------------------------------------------------
//    옵션 관리
//--------------------------------------------------------------------------------------------------

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_qt, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case save:
                saveDialog();
                break;
            case R.id.temp_save:
                temp_save();
                changeEditableIcon(item);
                break;
            case R.id.copy:
                qt_view.setClipboardEnabled();
                break;
            case R.id.delete:
                deleteDialog();
                break;
        }
        return true;
    }

//--------------------------------------------------------------------------------------------------
//    저장, 삭제, 복사
//--------------------------------------------------------------------------------------------------

    // 저장 대화상자
    @Override
    public void saveDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("저장하시겠습니까?");
        dialog.setNegativeButton("아니오", null);
        dialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                save();
            }
        });
        dialog.show();
    }

    // 저장
    @Override
    public void save(){
        QT_Lab.getQTLAB(getContext()).addQT(qt);
        removeTempSaved();
        // TODO 날짜 변경해주기. UUID 값 없애기
//        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new QT_Fragment()).commit();
    }

    // 삭제 대화상자
    @Override
    public void deleteDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("삭제하시겠습니까");
        dialog.setNegativeButton("아니오", null);
        dialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delete();
            }
        });
        dialog.show();
    }

    // 삭제
    @Override
    public void delete(){
        removeTempSaved();
//        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new QT_Fragment()).commit();
    }

    // 클립보드 -- copy
    @Override
    public void copy(){
        String clipped = "";
        if(qt_view.getWeekCheck().isChecked()){
            clipped = clipped + "- 이번 주 말씀 \n" + qt_view.getWeekTxt().getText().toString() + "\n";
        }
        if(qt_view.getQtCheck().isChecked()){
            clipped = clipped + "- 오늘 말씀 \n" + qt_view.getQtTxt().getText().toString() +"\n";
        }
        if(qt_view.getThanksCheck().isChecked()){
            clipped = clipped + "- 감사 \n" + qt_view.getThanksTxt().getText().toString() + "\n";
        }
        if(qt_view.getPrayerCheck().isChecked()){
            clipped = clipped + "- 기도 \n" + qt_view.getPrayerTxt().getText().toString() + "\n";
        }
        if(qt_view.getJournalCheck().isChecked()){
            clipped = clipped + "- 일기 \n" + qt_view.getJournalTxt().getText().toString();
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

//--------------------------------------------------------------------------------------------------
//    임시 저장소 관리
//--------------------------------------------------------------------------------------------------

    // 임시 저장소 관리
    public void temp_save(){
        if(status){
            qt_view.setEditable();
            status = false;
            Toast.makeText(getContext(), "쓰기", Toast.LENGTH_SHORT).show();
        } else {
            qt_view.setEditDisabled();
            status = true;
            setTempSave();
            Toast.makeText(getContext(), "임시 저장", Toast.LENGTH_SHORT).show();
        }
    }

    // 임시 저장소 관리 -- 저장하기
    @Override
    public void setTempSave(){  // TODO 1. 저장하지 않고 넘어갈 때  2. 임시저장 버튼을 누를 때 에 설정해야 한다.
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(WEEK, qt.getWeek());
        editor.putString(QT, qt.getQt());
        editor.putString(THANKS, qt.getThanks());
        editor.putString(PRAYER, qt.getPrayer());
        editor.putString(JOURNAL, qt.getJournal());
        editor.commit();

    }

    // 임시 저장소 관리 -- 불러오기
    @Override
    public void loadTempSaved(){
        String week = sp.getString(WEEK, "");
        String temp_qt = sp.getString(QT, "");
        String thanks = sp.getString(THANKS, "");
        String prayer = sp.getString(PRAYER, "");
        String journal = sp.getString(JOURNAL, "");

        qt.setWeek(week);
        qt.setQt(temp_qt);
        qt.setThanks(thanks);
        qt.setPrayer(prayer);
        qt.setJournal(journal);

    }

    // 임시 저장소 관리 -- 삭제
    @Override
    public void removeTempSaved(){
        // 사실 삭제는 아니고 임시 저장소의 값을 0으로 바꿔주는 것이다. 이게 딜레마인데, edittext 값이 변경되면 바로 qt 객체에
        // 저장되도록 설계를 해 놔서 remove 해도 다시 값이 설정이 되버린다.
        qt_view.getWeekTxt().setText("");
        qt_view.getQtTxt().setText("");
        qt_view.getThanksTxt().setText("");
        qt_view.getPrayerTxt().setText("");
        qt_view.getJournalTxt().setText("");
    }

    // 임시 저장소 관리 -- 자동 저장
    public void onPause(){
        super.onPause();
        setTempSave();
    }

    // 임시 저장소 관리 -- 아이콘 바꾸기
    @Override
    public void changeEditableIcon(MenuItem item){
        switch (item.getItemId()){
            case R.id.temp_save:
                if(iconStatus == true) {
                    item.setIcon(R.drawable.ic_action_read);
                    iconStatus = false;
                } else {
                    item.setIcon(R.drawable.ic_action_edit);
                    iconStatus = true;
                }
                break;
        }
    }

//--------------------------------------------------------------------------------------------------
//    데이터 받아오기(V -- > P) : 실제 실행되는 곳은 당연히 뷰이다.
//--------------------------------------------------------------------------------------------------

    // 뷰로부터 데이터 받아오기 -- 뷰에서 불러와서 값만 넣어주는 것임
    @Override
    public void get_WeekData_fromView(String title) {
        qt.setWeek(title);
    }

    @Override
    public void get_QTData_fromView(String inputQt) {
        qt.setQt(inputQt);
    }

    @Override
    public void get_PrayerData_fromView(String prayer) {
        qt.setPrayer(prayer);
    }

    @Override
    public void get_ThanksData_fromView(String thanks) {
        qt.setThanks(thanks);
    }

    @Override
    public void get_JournalData_fromView(String journal) {
        qt.setJournal(journal);
    }

//--------------------------------------------------------------------------------------------------
//    데이터 뿌려주기(P -- >V) : 메소드는 뷰에 있고, 실행되는 곳은 당연히 데이터가 있는 프레젠터
//--------------------------------------------------------------------------------------------------

    // 화면에 보여질 데이터 뷰에 뿌려주기
    public void out(){
        qt_view.loadWeekData_toView(qt.getWeek());
        qt_view.loadQTData_toView(qt.getQt());
        qt_view.loadThanksData_toView(qt.getThanks());
        qt_view.loadPrayerData_toView(qt.getPrayer());
        qt_view.loadJournalData_toView(qt.getJournal());
        qt_view.loadDate_toView(sdf(qt.getDate()));
    }

    public String sdf(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String result = sdf.format(date);
        return result;
    }

//--------------------------------------------------------------------------------------------------
//      for 인터페이스
//--------------------------------------------------------------------------------------------------

    @Override
    public View returnView(){
        return view;
    }

    @Override
    public Activity returnContext(){
        return getActivity();
    }


}
