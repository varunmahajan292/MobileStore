package com.example.meraphone;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class dashboardFragment extends Fragment {
    FirebaseAuth mAuth;
    TextView newPhone, newAccessories, phone, accessories, orders, logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View view =  inflater.inflate(R.layout.fragment_dashboard, container, false);


     newPhone = view.findViewById(R.id.newPhone);
     newAccessories = view.findViewById(R.id.newAccessories);
     phone = view.findViewById(R.id.phone);
     accessories  = view.findViewById(R.id.accessories);
     orders  = view.findViewById(R.id.orders);
     logout  = view.findViewById(R.id.logout);

        mAuth = FirebaseAuth.getInstance();
     newPhone.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Admin_Tasks_activity.fm.beginTransaction().replace(R.id.frag_cont_page_adm, new Admin_add_phones(), null).commit();

         }
     });

     newAccessories.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Admin_Tasks_activity.fm.beginTransaction().replace(R.id.frag_cont_page_adm, new Admin_add_access(), null).commit();

         }
     });


     phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admin_Tasks_activity.fm.beginTransaction().replace(R.id.frag_cont_page_adm, new fragmentPhone(), null).commit();

            }
        });

        accessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admin_Tasks_activity.fm.beginTransaction().replace(R.id.frag_cont_page_adm, new fragmentAdminAccessoriesList(), null).commit();

            }
        });


        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(getActivity(), MainActivity.class));

                getActivity().finish();
            }
        });








        return view;
    }
}


