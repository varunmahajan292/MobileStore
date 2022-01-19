package com.example.meraphone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MeraPhonDrawerActivity extends AppCompatActivity {
    public static FragmentManager fm;
    private AppBarConfiguration mAppBarConfiguration;
    private FirebaseAuth mAuth;
    static String LoggedIn_User_Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mera_phon_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fm=getSupportFragmentManager();
        FloatingActionButton fab = findViewById(R.id.fab);
        mAuth = FirebaseAuth.getInstance();


        if(mAuth.getCurrentUser() == null)
        {
            //User NOT logged In
            this.finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }






        //Fetch the Display name of current User
        FirebaseUser user = mAuth.getCurrentUser();
        // Log.d("LOGGED", "FirebaseUser: " + user);

        if (user != null) {

           // LoggedIn_User_Email =user.getEmail();
            ((global_vars) this.getApplication()).setLoginUserID(user.getEmail());

        }



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_AboutUs, R.id.nav_Accessories,R.id.nav_AllGadgets,R.id.nav_Iphone,R.id.nav_Samsung,R.id.nav_MyProfile,R.id.nav_MyPurchases,R.id.nav_Logout)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mera_phon_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void logout(MenuItem item) {
        mAuth.signOut();
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }
}
