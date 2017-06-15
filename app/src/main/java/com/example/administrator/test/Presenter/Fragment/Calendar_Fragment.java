package com.example.administrator.test.Presenter.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.administrator.test.Interface.Main_Interface;
import com.example.administrator.test.Presenter.Activity.MainActivity;
import com.example.administrator.test.R;


public class Calendar_Fragment extends Fragment {
    Main_Interface di;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_calendar, container, false);

        di = (MainActivity) getActivity();
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_calendar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_hamburgur);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                di.openDrawer();
            }
        });

        CalendarView calendarView = new CalendarView(getContext());

        return view;
    }

}
