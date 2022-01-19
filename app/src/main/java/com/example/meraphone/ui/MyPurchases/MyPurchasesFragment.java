package com.example.meraphone.ui.MyPurchases;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meraphone.Adapters.DetailsAdapter;
import com.example.meraphone.Adapters.PurchaseAdapter;
import com.example.meraphone.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPurchasesFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<String> nNames=new ArrayList<>();
    private ArrayList<String> mImagesURLs=new ArrayList<>();
    private ArrayList<String> ncost=new ArrayList<>();
    public MyPurchasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_purchases, container, false);
        recyclerView=view.findViewById(R.id.recycle_purchase);
        initImageBitmaps();
        return view;
    }
    private void initImageBitmaps() {
        mImagesURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQI65DvaaiXaJ_J_mROgOOY4g4mi-N-cPuk_cgmh75OWBZAlS6XVdPetdYhuLDhQ_e14A_ehsuw&usqp=CAc");
        nNames.add("iPhone XR 128GB");
        ncost.add("869");

        mImagesURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcR0xg0G4LK9bHBjNOWFY79FxEaWtyElhv8lhVqsUyGAd_UwyQL-8oAHKoIieGfXKsRk3Ef8az0&usqp=CAc");
        nNames.add("iPhone SE 256GB");
        ncost.add("809");

        mImagesURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQmzTS4a0P40EBmTwoYKAtmHPYHxYPOWWG9AOu576nKnKEsVPFXpDx_XS3mN2ucfi1HT2tjvC4&usqp=CAc");
        nNames.add("iPhone 11 Pro 64gb");
        ncost.add("1379");


        mImagesURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS9Fw59qIcKAR0nXPTHe2iD7LvVGxTTuplTPhWMSc5-aUhsx6HTLONgXMSzYt_AE025MYqEWh6P&usqp=CAc");
        nNames.add("iPhone 11 64GB");
        ncost.add("979");
        mImagesURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcR0xg0G4LK9bHBjNOWFY79FxEaWtyElhv8lhVqsUyGAd_UwyQL-8oAHKoIieGfXKsRk3Ef8az0&usqp=CAc");
        nNames.add("iPhone SE 256GB");
        ncost.add("809");

        mImagesURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQmzTS4a0P40EBmTwoYKAtmHPYHxYPOWWG9AOu576nKnKEsVPFXpDx_XS3mN2ucfi1HT2tjvC4&usqp=CAc");
        nNames.add("iPhone 11 Pro 64gb");
        ncost.add("1379");

        mImagesURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcR0xg0G4LK9bHBjNOWFY79FxEaWtyElhv8lhVqsUyGAd_UwyQL-8oAHKoIieGfXKsRk3Ef8az0&usqp=CAc");
        nNames.add("iPhone SE 256GB");
        ncost.add("809");

        mImagesURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQmzTS4a0P40EBmTwoYKAtmHPYHxYPOWWG9AOu576nKnKEsVPFXpDx_XS3mN2ucfi1HT2tjvC4&usqp=CAc");
        nNames.add("iPhone 11 Pro 64gb");
        ncost.add("1379");




        initRecylerView();

    }
    private void initRecylerView(){

        PurchaseAdapter adapter=new PurchaseAdapter(nNames,mImagesURLs,ncost,getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
    }
}
