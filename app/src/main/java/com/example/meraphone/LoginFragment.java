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
import com.example.meraphone.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    public EditText email, pass;
    public Button bt_login_login, bt_login_signup, bt_forgotpass;
    Db_frall db;
    TextView errorView;
    private static final String TAG = "SignInActivity";


    public FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        bt_login_login = view.findViewById(R.id.bt_login_login);
        bt_login_signup = view.findViewById(R.id.bt_login_signup);
        bt_forgotpass = view.findViewById(R.id.bt_forgotpass);
        email = view.findViewById(R.id.email);
        pass = view.findViewById(R.id.pass);
        errorView = view.findViewById(R.id.errorView);


mAuth = FirebaseAuth.getInstance();


        bt_login_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fm.beginTransaction().replace(R.id.frag_cont_page, new SignUpFragment(), null).commit();
            }
        });


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
                                        Log.d(TAG, "signInWithEmail:success");

                                        FirebaseUser user = mAuth.getCurrentUser();

                                        if (user != null) {
                                            if (user.isEmailVerified()) {


                                                //errorView.setText("");
                                               // errorView.setVisibility(View.GONE);
                                                Intent HomeActivity = new Intent(getContext(), MeraPhonDrawerActivity.class);
                                             //   setResult(RESULT_OK, null);
                                                startActivity(HomeActivity);
                                               getActivity().finish();


                                            } else {


                                                errorView.setText("Please Verify your EmailID and SignIn");

                                            }
                                        }

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());


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





        bt_forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fm.beginTransaction().replace(R.id.frag_cont_page, new ForgotPasswordFragment(), null).commit();
            }
        });
        return view;
    }
}
