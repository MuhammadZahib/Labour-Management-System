package com.example.labourmanagementsystem.SubClass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labourmanagementsystem.Models.SubModel;
import com.example.labourmanagementsystem.R;
import com.example.labourmanagementsystem.SubAdpater.ServicesAdapater;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServiceData extends AppCompatActivity implements ServicesAdapater.GetItem{

    RecyclerView rv;
    ServicesAdapater adapter;
    DatabaseReference database;
    ArrayList<SubModel> list =  new ArrayList<>();
    String userID,labourId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_data);

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        userID=user.getUid();

        rv=(RecyclerView)findViewById(R.id.servicerv);
        database=FirebaseDatabase.getInstance().getReference("Labours").child("Event Organizer");
       // rv.setHasFixedSize(true);

//        FirebaseRecyclerOptions<SubModel> options=
//                new FirebaseRecyclerOptions.Builder<SubModel>()
//                .setQuery(FirebaseDatabase.getInstance().getReference().child("Labours").child("Painter"),SubModel.class)
//                .build();




        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){


                    SubModel subModel= dataSnapshot.getValue(SubModel.class);
                    list.add(subModel);
                }
                adapter= new ServicesAdapater(ServiceData.this,list);

                rv.setLayoutManager(new LinearLayoutManager(ServiceData.this));

                rv.setAdapter(adapter);
                adapter.setClickListener(ServiceData.this);
                adapter.notifyDataSetChanged();

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
      // adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
       // adapter.stopListening();
    }

    @Override
    public void itemPosition(SubModel pos) {
        Dialog dialog = new Dialog(ServiceData.this);
        dialog.setContentView(R.layout.dialog_gig_data);
        TextView name= dialog.findViewById(R.id.dialog_name);
        TextView description=dialog.findViewById(R.id.dialog_description);
        Button sendRequest=dialog.findViewById(R.id.btnsendrequest);
        Button close=dialog.findViewById(R.id.btnclose);

        labourId=pos.getUserID();
        name.setText(pos.getCompanyName());
        description.setText(pos.getDescription());
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ServiceData.this,RequestActivity.class);
                intent.putExtra("Labour Id",labourId);
                intent.putExtra("Skill","Event Organizer");
                startActivity(intent);
            }
        });


        dialog.show();
    }
}