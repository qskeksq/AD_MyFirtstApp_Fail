package com.example.administrator.test.Presenter.Fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.administrator.test.R;


public class Settings_Fragment extends PreferenceFragment {

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

    public void onPause(){
        super.onPause();

        getFragmentManager().beginTransaction().remove(this).commit();

    }

}
