package com.example.administrator.test.Presenter.Fragment.ListFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.test.Model.Words;
import com.example.administrator.test.Model.Words_Lab;
import com.example.administrator.test.Presenter.Adapter.Words_Adapter;
import com.example.administrator.test.R;

import java.util.List;

public class List_Words_Fragment extends Fragment {

    RecyclerView recyclerView;
    List<Words> datas;
    Words_Adapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_list_words, container, false);

        // 1.
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_words);

        // 2.
        datas = Words_Lab.getWordsLAB(getContext()).getWords();

        // 3.
        adapter = new Words_Adapter(datas, getActivity().getSupportFragmentManager());

        // 4.
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

}
