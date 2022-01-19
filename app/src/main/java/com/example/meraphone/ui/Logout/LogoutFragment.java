package com.example.meraphone.ui.Logout;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meraphone.MeraPhonDrawerActivity;
import com.example.meraphone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogoutFragment extends Fragment {

    public LogoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_logout, container, false);
        Intent i = new Intent(getActivity(), MeraPhonDrawerActivity.class);
        startActivity(i);
        return view;
    }
}
