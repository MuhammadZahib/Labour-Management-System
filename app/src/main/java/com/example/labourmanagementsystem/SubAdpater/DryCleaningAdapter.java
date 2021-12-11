package com.example.labourmanagementsystem.SubAdpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.labourmanagementsystem.Models.CarWashModel;
import com.example.labourmanagementsystem.Models.SubModel;
import com.example.labourmanagementsystem.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DryCleaningAdapter extends RecyclerView.Adapter<DryCleaningAdapter.myviewholder> {


    Context context;
    ArrayList<CarWashModel> list= new ArrayList<>();

    private GetItem getItem;
    public interface GetItem{
        void itemPosition(CarWashModel pos);
    }
    public void setClickListener(GetItem getItem){
        this.getItem=getItem;
    }

    public DryCleaningAdapter(Context context, ArrayList<CarWashModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.service_sample,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        CarWashModel model=list.get(position);
        holder.description.setText(model.getDescription());
        holder.city.setText(model.getCity());
        holder.name.setText(model.getCompanyName());
        holder.contact.setText(model.getContact());
        Glide.with(holder.image.getContext()).load(model.getImage()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItem.itemPosition(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class myviewholder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView name,city,contact,description;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.maincircle);
            name=itemView.findViewById(R.id.companyname);
            city=itemView.findViewById(R.id.cityname);
            contact=itemView.findViewById(R.id.contact);
            description=itemView.findViewById(R.id.description);

        }
    }
}
