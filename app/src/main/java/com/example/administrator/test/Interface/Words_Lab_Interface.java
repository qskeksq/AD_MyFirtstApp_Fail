package com.example.administrator.test.Interface;

import android.content.Context;

import com.example.administrator.test.Model.QT;
import com.example.administrator.test.Model.Words;
import com.example.administrator.test.Model.Words_Lab;

import java.util.List;

/**
 * Created by Administrator on 2017-06-14.
 */

public interface Words_Lab_Interface {

    Words_Lab getWordsLAB(Context context); // 싱글턴 반환 함수   TODO 근데 인터페이스는 QTLAB 이전에 만들어 질텐데 어떻게 이걸 미리 만들지?
    void addWords(Words words);             // 추가
    void deleteWords(Words words);          // 업데이트
    void deleteQT(QT qt);                   // 삭제
    Words getData(int id);                  // 아이디로 조회
    List<Words> getWords()        ;          // 전체를 리스트로 반환

}
