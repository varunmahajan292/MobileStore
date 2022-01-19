package com.example.meraphone.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.meraphone.Admin_Tasks_activity;
import com.example.meraphone.Admin_add_phones;
import com.example.meraphone.R;
import com.example.meraphone.ShowDetailsActivity;
import com.example.meraphone.dashboardFragment;
import com.example.meraphone.fragmentAdminAccessoriesList;
import com.example.meraphone.fragmentPhone;
import com.example.meraphone.model.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class adapterAdminPhoneList extends RecyclerView.Adapter<adapterAdminPhoneList.ViewHolder> {
    // private ArrayList<String> mImageNames=new ArrayList<>();
    // private ArrayList<String> mImages=new ArrayList<>();
    //  private ArrayList<String> mcost=new ArrayList<>();
    //  private Context context;
    private ArrayList<Product> modelArrayList;
    Context context;
    String collection;

    public adapterAdminPhoneList(ArrayList<Product> modelArrayList, Context context, String collection) {
        this.modelArrayList = modelArrayList;
        this.context = context;
        this.collection = collection;
    }

    @NonNull
    @Override
    public adapterAdminPhoneList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_list, parent,false);

        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final adapterAdminPhoneList.ViewHolder holder, int position) {

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

        holder.cost.setText(objectModel.getItemPrice());
        holder.itemname.setText(objectModel.getItemName());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(),"click on item: "+objectModel.getId(),Toast.LENGTH_LONG).show();

                FirebaseFirestore db= FirebaseFirestore.getInstance();
                db.collection(collection).document(objectModel.getId()).delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(context, "Product deleted", Toast.LENGTH_LONG).show();
                                    if(collection.equals("Product")) {
                                        Admin_Tasks_activity.fm.beginTransaction().replace(R.id.frag_cont_page_adm, new fragmentPhone(), null).commit();
                                    }
                                    else
                                    {
                                        Admin_Tasks_activity.fm.beginTransaction().replace(R.id.frag_cont_page_adm, new fragmentAdminAccessoriesList(), null).commit();

                                    }
                                }

                            }
                        });

            }
        });


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(),"click on item: "+objectModel.getId(),Toast.LENGTH_LONG).show();

                Intent intent = new Intent(context, ShowDetailsActivity.class);
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
        TextView itemname,cost, delete;
        ImageView img ;
        RelativeLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            itemname=itemView.findViewById(R.id.itemname);
            cost=itemView.findViewById(R.id.cost);
            layout=itemView.findViewById(R.id.layout);
            delete = itemView.findViewById(R.id.delete);




        }
    }
}
