package com.example.administrator.test.Presenter.Fragment.ListFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.test.Model.QT;
import com.example.administrator.test.Model.QT_Lab;
import com.example.administrator.test.Presenter.Adapter.QT_Adapter;
import com.example.administrator.test.R;

import java.util.List;

/**
 * Created by Administrator on 2017-06-05.
 */

public class List_QT_Fragment extends Fragment {

    RecyclerView recyclerView;
    List<QT> datas;
    QT_Adapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        Toast.makeText(getContext(), "List_QT_Fragment onCreateView", Toast.LENGTH_SHORT).show();

        View view = inflater.inflate(R.layout.fragment_list_qt, container, false);

        // 1.
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_qt);

        // 2.
        datas = QT_Lab.getQTLAB(getContext()).getQTs();

        // 3.
        adapter = new QT_Adapter(datas, getActivity().getSupportFragmentManager());

        // 4.
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getContext(), "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(getContext(), "onPause", Toast.LENGTH_SHORT).show();
    }



}
