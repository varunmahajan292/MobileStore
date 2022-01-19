package com.example.meraphone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.meraphone.Adapters.DetailsAdapter;
import com.example.meraphone.database.Db_frall;
import com.example.meraphone.model.Product;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Other_phones extends AppCompatActivity {
    private RecyclerView recyclerView;

    private DetailsAdapter rvAdapter;
String brand;

    FirebaseFirestore db;
    ArrayList<Product> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_phones);
        recyclerView=findViewById(R.id.recycle_others);

        //recyclerView=view.findViewById(R.id.recycle_iphone);

        brand = getIntent().getStringExtra("brand");
        setTitle(brand);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvAdapter =new DetailsAdapter(list, getApplicationContext());

        recyclerView.setAdapter(rvAdapter);

        db = FirebaseFirestore.getInstance();


        db.collection("Product").whereEqualTo("category",brand).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> list1 = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : list1) {

                                Product p = d.toObject(Product.class);
                                p.setItemName(d.getString("itemName"));
                                p.setItemRating(d.getString("itemRating"));
                                p.setItemPrice(d.getString("itemPrice"));
                                p.setItemImage(d.getString("itemImage"));
                                p.setId(d.getId());
                                list.add(p);
                            }
                            rvAdapter.notifyDataSetChanged();
                        }
                    }
                });




    }
}