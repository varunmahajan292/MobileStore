package com.example.meraphone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    public static FragmentManager fm;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        faltu_context.context=getApplicationContext();

      /*  mAuth  = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            Intent MainActivity = new Intent(getApplicationContext(), MeraPhonDrawerActivity.class);
            startActivity(MainActivity);
            finish();
        }
*/



            fm =  getSupportFragmentManager();
        if (findViewById(R.id.frag_cont_page)!=null)
        {
            if(savedInstanceState != null)
            { return;
            }
            //////////default fragment///////////////////
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            LoginFragment lg = new LoginFragment();
            //  Upload_Car_Frag2 lg= new Upload_Car_Frag2();
            fragmentTransaction.add(R.id.frag_cont_page, lg, null);
            fragmentTransaction.commit();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menuuser, menu);

        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
//respond to menu item selection
        switch (item.getItemId()) {
            case R.id.admlog:
                MainActivity.fm.beginTransaction().replace(R.id.frag_cont_page,new Admin_login_frag(), null).commit();
                // startActivity(new Intent(this,AdminTasks.class));
                return true;
            case R.id.Userlog:
                MainActivity.fm.beginTransaction().replace(R.id.frag_cont_page,new LoginFragment(), null).commit();
                // startActivity(new Intent(this,AdminTasks.class));
                return true;
            case R.id.Logout:
                finish();
                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
