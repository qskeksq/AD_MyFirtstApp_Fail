package com.example.administrator.test.Model;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QT_Lab {

    Context context;
    DBHelper helper;
    public static QT_Lab lab;
    Dao<QT, Integer> dao;
    Handler handler = new Handler();

    public static QT_Lab getQTLAB(Context context){
        if(lab == null){
            lab = new QT_Lab(context);
        }
        return lab;
    }

    public QT_Lab(Context context){
        this.context = context;
        helper = DBHelper.getInstance(context);
        try {
            dao = helper.getDao(QT.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addQT(final QT qt){
        new Thread(){
            @Override
            public void run() {
                try {
                    dao.create(qt);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "저장되었습니다", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    // 업데이트
    public void updateQT(final QT qt){
        new Thread(){
            @Override
            public void run() {
                try {
                    dao.update(qt);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "업데이드 완료", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    // 삭제
    public void deleteQT(final QT qt){
        new Thread(){
            @Override
            public void run() {
                try {
                    dao.delete(qt);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "삭제되었습니다", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    // 한 개 조회
    public QT getData(QT qt){
        QT item = new QT();
        try{
            item = dao.queryForId(qt.getId());
        } catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }

    public QT getData(int id){
        QT item = new QT();
        try{
            item = dao.queryForId(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }

    // 여러개 조회
    public List<QT> getQTs(){
        List<QT> datas = new ArrayList<>();
        try{
            datas = dao.queryForAll();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return datas;
    }
}
