package com.example.labourmanagementsystem.Adapters;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.labourmanagementsystem.Models.CarWashModel;
import com.example.labourmanagementsystem.Models.InboxModel;
import com.example.labourmanagementsystem.Models.SubModel;
import com.example.labourmanagementsystem.Models.TaskStatusModel;
import com.example.labourmanagementsystem.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TaskStatusAdapter extends RecyclerView.Adapter<TaskStatusAdapter.myviewholder> {

    private GetItem getItem;
    private Bill bill;

    Context context;
    ArrayList<TaskStatusModel> list= new ArrayList<>();

    public interface GetItem{
        void itemPosition(TaskStatusModel pos);
    }

    public interface Bill{
        void item(TaskStatusModel pos);
    }

    public void setClick(Bill bill){
        this.bill=bill;
    }
    public void setClickListener(GetItem getItem){
        this.getItem=getItem;
    }

    public TaskStatusAdapter(Context context, ArrayList<TaskStatusModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.layout_task_status,parent,false);
        return new myviewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        TaskStatusModel model=list.get(position);
        holder.name.setText(model.getCustomerName());
        holder.skill.setText(model.getSkill());
        holder.task_status.setText(model.getStatus());
        //  Glide.with(holder.image.getContext()).load(model.getImage()).into(holder.image);

        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bill.item(list.get(position));
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
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

        TextView name,skill,task_status;
        EditText edit_price;
        Button edit,detail,set;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            //  image=itemView.findViewById(R.id.requestcircle);
            name=itemView.findViewById(R.id.cname_task);
            skill=itemView.findViewById(R.id.skill_task);
            task_status=itemView.findViewById(R.id.task_stauts);
            edit_price=itemView.findViewById(R.id.new_price);
            edit=itemView.findViewById(R.id.edit_task_status);
            detail=itemView.findViewById(R.id.details_task_status);
            set=itemView.findViewById(R.id.btn_task_complete);

        }
    }
}

