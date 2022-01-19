package com.example.meraphone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meraphone.Adapters.adapterAdminPhoneList;
import com.example.meraphone.model.Product;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class fragmentAdminAccessoriesList extends Fragment {
    private RecyclerView recyclerView;
    private adapterAdminPhoneList rvAdapter;
    FirebaseFirestore db;
    ArrayList<Product> list = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_accessories_list, container, false);
        recyclerView=view.findViewById(R.id.recycle);



        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAdapter =new adapterAdminPhoneList(list, getContext(), "Accessories");

        recyclerView.setAdapter(rvAdapter);

        db = FirebaseFirestore.getInstance();


        db.collection("Accessories").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> list1 = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : list1) {

                                Product p = d.toObject(Product.class);
                                p.setItemName(d.getString("itemName"));
                                p.setItemPrice(d.getString("itemPrice"));
                                p.setItemImage(d.getString("itemImage"));
                                p.setId(d.getId());
                                list.add(p);
                            }
                            rvAdapter.notifyDataSetChanged();
                        }
                    }
                });







        return view;
    }



}


