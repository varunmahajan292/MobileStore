package com.example.meraphone.ui.Samsung;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.meraphone.Adapters.DetailsAdapter;
import com.example.meraphone.R;
import com.example.meraphone.database.Db_frall;
import com.example.meraphone.model.Product;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SamsungFragment extends Fragment {
    private RecyclerView recyclerView;
    private Db_frall databaseHandler;
    private DetailsAdapter rvAdapter;


    FirebaseFirestore db;
    ArrayList<Product> list = new ArrayList<>();

    public SamsungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view=  inflater.inflate(R.layout.fragment_samsung, container, false);
        recyclerView=view.findViewById(R.id.recycle_samsung);
        //recyclerView=view.findViewById(R.id.recycle_iphone);

        //  rating=view.findViewById(R.id.rating);

        db = FirebaseFirestore.getInstance();


        db.collection("Product").whereEqualTo("category","Samsung").get()
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





        rvAdapter =new DetailsAdapter(list, getContext());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(rvAdapter);
        return view;
    }



}


