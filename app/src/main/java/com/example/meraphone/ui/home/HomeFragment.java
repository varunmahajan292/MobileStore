package com.example.meraphone.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meraphone.Adapters.Adapter_home;
import com.example.meraphone.Adapters.DetailsAdapter;
import com.example.meraphone.R;
import com.example.meraphone.model.Product;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView rec_topsales,rec_newarrivals,rec_Recommendations;

    private ArrayList<Product> list=new ArrayList<>();
DetailsAdapter adapter;
    FirebaseFirestore db;

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        rec_topsales=view.findViewById(R.id.rec_topsales);


        db = FirebaseFirestore.getInstance();

        db.collection("Product").get()
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
                            adapter.notifyDataSetChanged();
                        }
                    }
                });





        adapter =new DetailsAdapter(list, getContext());

        rec_topsales.setHasFixedSize(true);
        rec_topsales.setLayoutManager(new LinearLayoutManager(getContext()));
        rec_topsales.setAdapter(adapter);









/*



        adapter = new Adapter_home(list,getContext());

        rec_topsales.setAdapter(adapter);
        rec_topsales.setLayoutManager(new LinearLayoutManager(getContext()));
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rec_topsales.setLayoutManager(horizontalLayoutManagaer);

        //////for 2
        rec_newarrivals.setAdapter(adapter);
        rec_newarrivals.setLayoutManager(new LinearLayoutManager(getContext()));
        LinearLayoutManager horizontalLayoutManagaer1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rec_newarrivals.setLayoutManager(horizontalLayoutManagaer1);
        //for 3
        rec_Recommendations.setAdapter(adapter);
        rec_Recommendations.setLayoutManager(new LinearLayoutManager(getContext()));
        LinearLayoutManager horizontalLayoutManagaer2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rec_Recommendations.setLayoutManager(horizontalLayoutManagaer2);
*/


        return view;
    }


}
