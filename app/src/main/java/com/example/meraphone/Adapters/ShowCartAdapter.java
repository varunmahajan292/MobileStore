package com.example.meraphone.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.meraphone.R;
import com.example.meraphone.ShowDetailsActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShowCartAdapter extends RecyclerView.Adapter<ShowCartAdapter.ViewHolder> {
private ArrayList<String> mImageNames=new ArrayList<>();
private ArrayList<String> mImages=new ArrayList<>();
    private ArrayList<String> mcost=new ArrayList<>();
private Context context;

public ShowCartAdapter(ArrayList<String> mImageNames, ArrayList<String> mImages,ArrayList<String> mcost, Context context) {
        this.mImageNames = mImageNames;
        this.mImages = mImages;
    this.mcost = mcost;
        this.context = context;
        }

@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cart, parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).asBitmap().load(mImages.get(position)).into(holder.img);
        holder.imageName.setText(mImageNames.get(position));
    holder.cost.setText(mcost.get(position));

        }

@Override
public int getItemCount() {
        return mImageNames.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder{
    ImageView img;
    TextView imageName,cost;

    LinearLayout layoutclick;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        img=itemView.findViewById(R.id.img);
        imageName=itemView.findViewById(R.id.itemname);
        cost=itemView.findViewById(R.id.cost);
        layoutclick=itemView.findViewById(R.id.layoutclick);

    }

}
}
