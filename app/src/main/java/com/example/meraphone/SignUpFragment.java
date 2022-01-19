package com.example.meraphone;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.meraphone.database.Db_frall;
import com.example.meraphone.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {
    EditText fname,lname,email,pass,mobile;
    Button login,signup;

    public FirebaseAuth mAuth;
    FirebaseFirestore db;

    TextView errorView;
    String emailString;


    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sign_up, container, false);
        fname=view.findViewById(R.id.fname);
        lname=(EditText)view.findViewById(R.id.lname);
        email=(EditText)view.findViewById(R.id.email);
        pass=(EditText)view.findViewById(R.id.pass);
        mobile=(EditText)view.findViewById(R.id.mobile);
        login=(Button) view.findViewById(R.id.login);
        signup=(Button)view.findViewById(R.id.signup);
        emailString= email.getText().toString();

        errorView = view.findViewById(R.id.errorView);
        mAuth = FirebaseAuth.getInstance();
     db = FirebaseFirestore.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fm.beginTransaction().replace(R.id.frag_cont_page,new LoginFragment(), null).commit();
            }
        });



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (email.getText().toString().contentEquals("")) {


                    errorView.setText("Email cannot be empty");


                } else if (pass.getText().toString().contentEquals("")) {


                    errorView.setText("Password cannot be empty");


                } else {


                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();

                                try {
                                    if (user != null)
                                        user.sendEmailVerification()
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {

                                                            loadOtherDetails();

                                                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                                                    getContext());

                                                            // set title
                                                            alertDialogBuilder.setTitle("Please Verify Your EmailID");

                                                            // set dialog message
                                                            alertDialogBuilder
                                                                    .setMessage("A verification Email Is Sent To Your Registered EmailID, please click on the link and Sign in again!")
                                                                    .setCancelable(false)
                                                                    .setPositiveButton("Sign In", new DialogInterface.OnClickListener() {
                                                                        public void onClick(DialogInterface dialog, int id) {

                                                                            MainActivity.fm.beginTransaction().replace(R.id.frag_cont_page, new LoginFragment(), null).commit();

                                                                        }
                                                                    });

                                                            // create alert dialog
                                                            AlertDialog alertDialog = alertDialogBuilder.create();

                                                            // show it
                                                            alertDialog.show();


                                                        }
                                                    }

                                                    private void loadOtherDetails() {

                                                        User user = new User();
                                                        user.setFirstName(fname.getText().toString());
                                                        user.setLastName(lname.getText().toString());
                                                        user.setNumber(mobile.getText().toString());
                                                        user.setEmail(email.getText().toString());

                                                        db.collection("users").document(user.getEmail()).set(user)
                                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {

                                                                    }
                                                                })
                                                                .addOnFailureListener(new OnFailureListener() {
                                                                    @Override
                                                                    public void onFailure(@NonNull Exception e) {

                                                                    }
                                                                });


                                                    }
                                                });

                                } catch (Exception e) {
                                    errorView.setText(e.getMessage());
                                }
                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(getContext(), "Failed. Try Again...",
                                        Toast.LENGTH_SHORT).show();

                                if (task.getException() != null) {
                                    errorView.setText(task.getException().getMessage());
                                    errorView.setVisibility(View.VISIBLE);
                                }

                            }

                        }
                    });

                }

            }
        });




        return view;
    }
}
