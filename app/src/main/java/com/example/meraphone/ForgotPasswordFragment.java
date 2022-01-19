package com.example.meraphone;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForgotPasswordFragment extends Fragment {
    EditText edit_email;
    Button recoverybtn, login;
    public FirebaseAuth mAuth;

    public ForgotPasswordFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_forgot_password, container, false);

        edit_email=(EditText)view.findViewById(R.id.edit_email);
        recoverybtn=(Button)view.findViewById(R.id.recoverybtn);
        login = view.findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();



               recoverybtn.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       mAuth.sendPasswordResetEmail(edit_email.getText().toString())
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
                                                       MainActivity.fm.beginTransaction().replace(R.id.frag_cont_page, new LoginFragment(), null).commit();
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


               login.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       MainActivity.fm.beginTransaction().replace(R.id.frag_cont_page, new LoginFragment(), null).commit();

                   }
               });

        return view;
    }


}
