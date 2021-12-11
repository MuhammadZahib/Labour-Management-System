package com.example.labourmanagementsystem.sub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.labourmanagementsystem.Adapters.CleaningAdapter;
import com.example.labourmanagementsystem.Adapters.EventAdapter;
import com.example.labourmanagementsystem.Models.PojoModel;
import com.example.labourmanagementsystem.R;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity implements EventAdapter.getItem {

    RecyclerView rvevent;
    ArrayList<PojoModel> listevent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        rvevent=findViewById(R.id.rvevent);


        ArrayList<PojoModel> listevent= new ArrayList<>();

        listevent.add(new PojoModel(R.drawable.eventdesigner,"Event Organizers/پروگرام آرگنائزر"));


        EventAdapter adapter= new EventAdapter(listevent);
        rvevent.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        rvevent.setLayoutManager(linearLayoutManager);

    }





}