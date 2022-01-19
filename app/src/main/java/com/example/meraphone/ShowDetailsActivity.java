package com.example.meraphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowDetailsActivity extends AppCompatActivity {
    private Button btn_cart;
    TextView name;
    String productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        btn_cart=findViewById(R.id.btn_cart);
        name = findViewById(R.id.name);

        productId =getIntent().getStringExtra("productId");
        name.setText(productId);


    btn_cart.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i =new Intent(getApplicationContext(), ShowCartActivity.class);
            startActivity(i);
        }
    });
    }

}
