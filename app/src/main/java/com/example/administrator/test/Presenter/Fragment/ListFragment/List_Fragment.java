package com.example.administrator.test.Presenter.Fragment.ListFragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.test.Interface.Main_Interface;
import com.example.administrator.test.Presenter.Activity.MainActivity;
import com.example.administrator.test.R;

import java.util.ArrayList;
import java.util.List;


public class List_Fragment extends Fragment {

    Main_Interface di;
    ViewPager viewPager;
    Fragment qt, words, decision;
    List<Fragment> datas;
    TabLayout tab;
    Toolbar toolbar;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Toast.makeText(getContext(), "onCreateView", Toast.LENGTH_SHORT).show();

        view = inflater.inflate(R.layout.fragment_list, container, false);

        // 1.
        init();

        // 2.
        datas = new ArrayList<>();

        qt = new List_QT_Fragment();
        words = new List_Words_Fragment();
        decision = new List_Decision_Fragment();
        datas.add(qt);
        datas.add(words);
        datas.add(decision);

        // 3.
        PagerAdapter adapter = new PagerAdapter(getActivity().getSupportFragmentManager());

        // 4.
        viewPager.setAdapter(adapter);


        // 5.
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        return view;
    }

    // 0. init
    public void init(){
        di = (MainActivity) getActivity();
        findAddress();
        setTab();
        setToolbar();
    }

    // 1. 주소
    public void findAddress(){
        toolbar = (Toolbar) view.findViewById(R.id.toolbar_list);
        tab = (TabLayout) view.findViewById(R.id.tab);
        viewPager = (ViewPager) view.findViewById(R.id.list_fragment_container);
    }

    // 3. set 함수
    public void setTab(){
        tab.addTab(tab.newTab().setText("QT"));
        tab.addTab(tab.newTab().setText("Words"));
        tab.addTab(tab.newTab().setText("Decision"));
    }

    public void setToolbar(){
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_hamburgur);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                di.openDrawer();
            }
        });
    }

//--------------------------------------------------------------------------------------------------
//    페이저 어댑터 관리
//--------------------------------------------------------------------------------------------------

    class PagerAdapter extends FragmentStatePagerAdapter {


        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return datas.get(position);
        }

        @Override
        public int getCount() {
            return datas.size();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(getContext(), "onPause", Toast.LENGTH_SHORT).show();
        datas.clear();
    }
}
