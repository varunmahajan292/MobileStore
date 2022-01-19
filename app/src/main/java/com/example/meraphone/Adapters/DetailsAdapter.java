package com.example.meraphone.Adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Rating;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.meraphone.Model;
import com.example.meraphone.R;
import com.example.meraphone.ShowDetailsActivity;
import com.example.meraphone.model.Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {
   
   private ArrayList<Product> modelArrayList;
   Context context;
    public DetailsAdapter(ArrayList<Product> modelArrayList, Context context) {
        this.modelArrayList = modelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_accessories, parent,false);

        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final Product objectModel =modelArrayList.get(position);


        FirebaseStorage firebaseStorage;
        StorageReference storageReference;

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

//        StorageReference imageRef = storageReference.child("Images/my.png");
        StorageReference imageRef = storageReference.child("Images/"+objectModel.getItemImage());

        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Glide.with(context.getApplicationContext())
                        .load(uri)
                        .into(holder.img);

                //Toast.makeText(getApplicationContext(),"Success.",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Toast.makeText(getApplicationContext(),"fail.",Toast.LENGTH_SHORT).show();
            }
        });



      //  holder.img.setImageBitmap(objectModel.getItemImage());
        holder.rating.setRating(Float.parseFloat(objectModel.getItemRating()));
        holder.cost.setText(objectModel.getItemPrice());
        holder.itemname.setText(objectModel.getItemName());
        holder.faltu.setText(String.valueOf(objectModel.getId()));
        holder.layoutclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  //Toast.makeText(view.getContext(),"click on item: "+objectModel.getId(),Toast.LENGTH_LONG).show();

                  Intent intent = new Intent(context,ShowDetailsActivity.class);
                  intent.putExtra("productId",objectModel.getId());
                  context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        if(modelArrayList!=null){
            return modelArrayList.size();
        }
        else
        {return 0;}
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemname,count,cost,faltu;
        Button decrease,add;
        RatingBar rating;
        ImageView img ;
        Button layoutclick;
        int counter=0;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            itemname=itemView.findViewById(R.id.itemname);
           rating=itemView.findViewById(R.id.rating);
            cost=itemView.findViewById(R.id.cost);
            faltu = itemView.findViewById(R.id.faltu);
            layoutclick=itemView.findViewById(R.id.details);


        }

    }




}
