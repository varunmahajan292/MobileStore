package com.example.meraphone.ui.MyProfile;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meraphone.LoginFragment;
import com.example.meraphone.MainActivity;
import com.example.meraphone.MeraPhonDrawerActivity;
import com.example.meraphone.R;
import com.example.meraphone.database.Db_frall;
import com.example.meraphone.faltu_context;
import com.example.meraphone.global_vars;
import com.example.meraphone.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends Fragment {
    public TextView email;
    public FirebaseAuth mAuth;
    public EditText fname, lname, mobile,pass,confirmPass;

    public Button updfateInfo, changePass;
    //Db_frall db;
    FirebaseFirestore db;
    public MyProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_profile, container, false);
        email=view.findViewById(R.id.email);
        fname=view.findViewById(R.id.fname);
        lname=view.findViewById(R.id.lname);
        mobile=view.findViewById(R.id.mobile);
        updfateInfo=view.findViewById(R.id.updfateInfo);
        changePass=view.findViewById(R.id.changePass);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        //getdata from db


        DocumentReference docRef = db.collection("users").document(((global_vars) getActivity().getApplication()).getLoginUserID().toString());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        // Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        User user = document.toObject(User.class);
                        email.setText(user.getEmail());
                        fname.setText(user.getFirstName());
                        lname.setText(user.getLastName());
                        mobile.setText(user.getNumber());
                    } else {
                        // Log.d(TAG, "No such document");
                    }
                } else {
                    // Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });




        updfateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DocumentReference docRef = db.collection("users").document(((global_vars) getActivity().getApplication()).getLoginUserID().toString());

// Update the timestamp field with the value from the server
                Map<String,Object> updates = new HashMap<>();
                updates.put("firstName", fname.getText().toString());
                updates.put("lastName", lname.getText().toString());
                updates.put("number", mobile.getText().toString());

                docRef.update(updates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getContext(),"Data Updated...",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(),"Data Not Updated. Try Again..,",Toast.LENGTH_SHORT).show();

                            }
                        });


            }
        });

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.sendPasswordResetEmail(((global_vars) getActivity().getApplication()).getLoginUserID().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

                                    // set title
                                    alertDialogBuilder.setTitle("Reset Password");

                                    // set dialog message
                                    alertDialogBuilder
                                            .setMessage("A Reset Password Link Is Sent To Your Registered EmailID")
                                            .setCancelable(false)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    // getActivity().finish();
                                                }
                                            });

                                    AlertDialog alertDialog = alertDialogBuilder.create();
                                    alertDialog.show();
                                }
                                else {
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

                                    // set title
                                    alertDialogBuilder.setTitle("Error");

                                    // set dialog message
                                    alertDialogBuilder
                                            .setMessage("A Reset Password Link Is Not Sent. Please Retry.")
                                            .setCancelable(false)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {

                                                }
                                            });

                                    AlertDialog alertDialog = alertDialogBuilder.create();
                                    alertDialog.show();
                                }

                            }
                        });

            }
        });


        return view;
    }
}
