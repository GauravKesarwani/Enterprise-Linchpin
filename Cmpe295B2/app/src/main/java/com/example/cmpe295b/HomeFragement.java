package com.example.cmpe295b;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cmpe295b.R;

public class HomeFragement extends Fragment {

    public HomeFragement(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragement_home, container, false);

        return rootView;
    }
}