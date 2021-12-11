package com.example.labourmanagementsystem.sub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.labourmanagementsystem.Adapters.BeautyAdapter;
import com.example.labourmanagementsystem.Adapters.FittingAdapter;
import com.example.labourmanagementsystem.Models.PojoModel;
import com.example.labourmanagementsystem.R;

import java.util.ArrayList;

public class FittingActivity extends AppCompatActivity {

    RecyclerView rvfitting;
    ArrayList<PojoModel> listfitting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitting);

        rvfitting=findViewById(R.id.rvfitting);


        ArrayList<PojoModel> listfitting= new ArrayList<>();

        listfitting.add(new PojoModel(R.drawable.carpenter,"Carpenter/بڑھئی"));
        listfitting.add(new PojoModel(R.drawable.interior,"Interior Designer/داخلی ڈیزائنر"));


        FittingAdapter adapter= new FittingAdapter(listfitting);
        rvfitting.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        rvfitting.setLayoutManager(linearLayoutManager);

    }
}