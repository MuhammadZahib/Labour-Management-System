package com.example.labourmanagementsystem.sub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.labourmanagementsystem.Adapters.DressAdapter;
import com.example.labourmanagementsystem.Adapters.RVAdapter;
import com.example.labourmanagementsystem.Models.PojoModel;
import com.example.labourmanagementsystem.R;

import java.util.ArrayList;

public class DressActivity extends AppCompatActivity {

    RecyclerView rvdress;
    ArrayList<PojoModel> listdress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dress);

        rvdress=findViewById(R.id.rvdress);


        ArrayList<PojoModel> listdress= new ArrayList<>();

        listdress.add(new PojoModel(R.drawable.dressmaker,"Dress Maker/درزی"));
        listdress.add(new PojoModel(R.drawable.tailor,"Tailor/درزی"));

        DressAdapter adapter= new DressAdapter(listdress);
        rvdress.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        rvdress.setLayoutManager(linearLayoutManager);


    }
}