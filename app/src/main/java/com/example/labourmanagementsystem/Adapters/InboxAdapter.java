package com.example.labourmanagementsystem.Adapters;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.labourmanagementsystem.Models.CarWashModel;
import com.example.labourmanagementsystem.Models.InboxModel;
import com.example.labourmanagementsystem.Models.SubModel;
import com.example.labourmanagementsystem.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.myviewholder> {

    private GetItem getItem;

    Context context;
    ArrayList<InboxModel> list= new ArrayList<>();

    public interface GetItem{
        void itemPosition(InboxModel pos);
    }
    public void setClickListener(GetItem getItem){
        this.getItem=getItem;
    }

    public InboxAdapter(Context context, ArrayList<InboxModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.layout_inbox_card,parent,false);
        return new myviewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        InboxModel model=list.get(position);
        holder.name.setText(model.getCustomerName());
      //  Glide.with(holder.image.getContext()).load(model.getImage()).into(holder.image);

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
        TextView name;
        TextView details;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

          //  image=itemView.findViewById(R.id.requestcircle);
            name=itemView.findViewById(R.id.txt_request_name);
            details=itemView.findViewById(R.id.btn_detail_request);

        }
    }
}

