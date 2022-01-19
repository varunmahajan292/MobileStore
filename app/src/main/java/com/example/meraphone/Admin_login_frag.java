package com.example.meraphone;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class Admin_login_frag extends Fragment {

    EditText pass, email;
    TextView errorView;
    Button bt_login_login;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_admin_login_frag , container, false);
        email= view.findViewById(R.id.email);
        pass=view. findViewById(R.id.pass);
        bt_login_login= view.findViewById(R.id.bt_login_login);
        errorView = view.findViewById(R.id.errorVie);

        mAuth = FirebaseAuth.getInstance();
        db  = FirebaseFirestore.getInstance();



        bt_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (email.getText().toString().contentEquals("")) {


                    errorView.setText("Email cant be empty");


                } else if (pass.getText().toString().contentEquals("")) {

                    errorView.setText("Password cant be empty");

                } else {


                    mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information


                                        FirebaseUser user = mAuth.getCurrentUser();

                                        if (user != null) {
                                            if (user.isEmailVerified()) {

                                                DocumentReference docRef = db.collection("users").document(email.getText().toString().trim());
                                                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                        User user = documentSnapshot.toObject(User.class);

                                                        if(user.isAdmin())
                                                        {
                                                            Intent HomeActivity = new Intent(getContext(), Admin_Tasks_activity.class);
                                                            //   setResult(RESULT_OK, null);
                                                            startActivity(HomeActivity);
                                                            getActivity().finish();
                                                        }
                                                        else {
                                                            errorView.setText("This Email Is Not SetUp For Admin Account.");
                                                        }
                                                    }
                                                });


                                                //errorView.setText("");
                                                // errorView.setVisibility(View.GONE);



                                            } else {


                                                errorView.setText("Please Verify your EmailID and SignIn");

                                            }
                                        }

                                    } else {
                                        // If sign in fails, display a message to the user.



                                        Toast.makeText(getContext(), "Authentication failed.",
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
