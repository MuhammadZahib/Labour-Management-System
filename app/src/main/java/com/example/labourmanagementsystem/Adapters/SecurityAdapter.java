package com.example.labourmanagementsystem.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labourmanagementsystem.Models.PojoModel;
import com.example.labourmanagementsystem.R;
import com.example.labourmanagementsystem.SubClass.CctvActivity;
import com.example.labourmanagementsystem.SubClass.GuardActivity;
import com.example.labourmanagementsystem.sub.SecurityActivity;

import java.util.ArrayList;

public class SecurityAdapter extends RecyclerView.Adapter<SecurityAdapter.viewHolder>{

    ArrayList<PojoModel> list;
    //Context context;

    public SecurityAdapter(ArrayList<PojoModel> list) {
        this.list = list;
        //  this.context = context;
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

        switch (position){

            case 0:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.getContext().startActivity(new Intent(v.getContext(), GuardActivity.class));
                    }
                });
                break;

            case 1:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.getContext().startActivity(new Intent(v.getContext(), CctvActivity.class));
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

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView);
            textView=itemView.findViewById(R.id.textView);
        }
    }

}


