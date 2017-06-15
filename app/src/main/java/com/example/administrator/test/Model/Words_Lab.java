package com.example.administrator.test.Model;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-06-12.
 */

public class Words_Lab {

    Context context;
    DBHelper helper;
    public static Words_Lab lab;
    Dao<Words, Integer> dao;
    Handler handler = new Handler();

    public static Words_Lab getWordsLAB(Context context){
        if(lab == null){
            lab = new Words_Lab(context);
        }
        return lab;
    }

    public Words_Lab(Context context){
        this.context = context;
        helper = DBHelper.getInstance(context);
        try {
            dao = helper.getDao(Words.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 추가
    public void addWords(final Words words){
        new Thread(){
            public void run() {
                try {
                    dao.create(words);
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
    public void updateWords(final Words words){
        new Thread(){
            public void run() {
                try {
                    dao.update(words);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "업데이트 완료", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    // 삭제
    public void deleteWords(final Words words){
        new Thread(){
            public void run() {
                try {
                    dao.delete(words);
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
    public Words getData(Words words){
        Words item = new Words();
        try{
            item = dao.queryForId(words.getId());
        } catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }

    public Words getData(int id){
        Words item = new Words();
        try{
            item = dao.queryForId(id);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }

    // 여러개 조회
    public List<Words> getWords(){
        List<Words> datas = new ArrayList<>();
        try{
            datas = dao.queryForAll();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return datas;
    }
}
