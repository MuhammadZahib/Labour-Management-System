package com.example.labourmanagementsystem.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labourmanagementsystem.CleaningActivity;
import com.example.labourmanagementsystem.Models.PojoModel;
import com.example.labourmanagementsystem.R;
import com.example.labourmanagementsystem.sub.BeautyActivity;
import com.example.labourmanagementsystem.sub.ConstructionActivity;
import com.example.labourmanagementsystem.sub.DressActivity;
import com.example.labourmanagementsystem.sub.EngineerActivity;
import com.example.labourmanagementsystem.sub.EventActivity;
import com.example.labourmanagementsystem.sub.FittingActivity;
import com.example.labourmanagementsystem.sub.LessonActivity;
import com.example.labourmanagementsystem.sub.PackagingActivity;
import com.example.labourmanagementsystem.sub.SecurityActivity;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.viewHolder> {

    ArrayList<PojoModel> list;

    public RVAdapter(ArrayList<PojoModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_rv,parent ,false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        PojoModel model=list.get(position);
        holder.imageView.setImageResource(model.getPic());
        holder.textView.setText(model.getText());

        switch(position){

            case 0:
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      v.getContext().startActivity(new Intent(v.getContext(),CleaningActivity.class));
                    }
                });
                break;
            case 1:
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.getContext().startActivity(new Intent(v.getContext(), EngineerActivity.class));
                    }
                });

                break;
            case 2:
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.getContext().startActivity(new Intent(v.getContext(), BeautyActivity.class));
                    }
                });

                break;
            case 3:
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.getContext().startActivity(new Intent(v.getContext(), ConstructionActivity.class));
                    }
                });
                break;
            case 4:
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.getContext().startActivity(new Intent(v.getContext(), DressActivity.class));
                    }
                });
                break;
            case 5:
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.getContext().startActivity(new Intent(v.getContext(), PackagingActivity.class));
                    }
                });
                break;
            case 6:
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.getContext().startActivity(new Intent(v.getContext(), EventActivity.class));
                    }
                });
                break;
            case 7:
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.getContext().startActivity(new Intent(v.getContext(), SecurityActivity.class));
                    }
                });
                break;
            case 8:
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.getContext().startActivity(new Intent(v.getContext(), FittingActivity.class));
                    }
                });
                break;
            case 9:
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.getContext().startActivity(new Intent(v.getContext(), LessonActivity.class));
                    }
                });
                break;
            default:
                break;



        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView);
            textView=itemView.findViewById(R.id.textView);
        }
    }
}
