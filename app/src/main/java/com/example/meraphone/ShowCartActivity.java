package com.example.meraphone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.meraphone.Adapters.DetailsAdapter;
import com.example.meraphone.Adapters.ShowCartAdapter;

import java.util.ArrayList;

public class ShowCartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<String> nNames=new ArrayList<>();
    private ArrayList<String> mImagesURLs=new ArrayList<>();
    private ArrayList<String> ncost=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cart);
        recyclerView=findViewById(R.id.recyclerView);
        initImageBitmaps();
    }
    private void initImageBitmaps() {

        mImagesURLs.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        nNames.add("Trondheim");
        ncost.add("456");

        mImagesURLs.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        nNames.add("Portugal");
        ncost.add("678");

        mImagesURLs.add("https://i.redd.it/j6myfqglup501.jpg");
        nNames.add("Rocky Mountain National Park");
        ncost.add("893");


        mImagesURLs.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        nNames.add("Mahahual");
        ncost.add("356");

        mImagesURLs.add("https://i.redd.it/k98uzl68eh501.jpg");
        nNames.add("Frozen Lake");
        ncost.add("556");


        mImagesURLs.add("https://i.redd.it/glin0nwndo501.jpg");
        nNames.add("White Sands Desert");
        ncost.add("786");

        mImagesURLs.add("https://i.redd.it/obx4zydshg601.jpg");
        nNames.add("Austrailia");
        ncost.add("354");

        mImagesURLs.add("https://i.imgur.com/ZcLLrkY.jpg");
        nNames.add("Washington");
        ncost.add("267");



        initRecylerView();

    }
    private void initRecylerView(){

        ShowCartAdapter adapter=new ShowCartAdapter(nNames,mImagesURLs,ncost,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(ShowCartActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
    }
}
