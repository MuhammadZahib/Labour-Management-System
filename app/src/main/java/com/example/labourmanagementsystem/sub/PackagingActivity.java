package com.example.labourmanagementsystem.sub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.labourmanagementsystem.Adapters.EngineerAdapter;
import com.example.labourmanagementsystem.Adapters.PackageAdapter;
import com.example.labourmanagementsystem.Models.PojoModel;
import com.example.labourmanagementsystem.R;

import java.util.ArrayList;

public class PackagingActivity extends AppCompatActivity {
    RecyclerView rvpackage;
    ArrayList<PojoModel> listpackage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packaging);

        rvpackage=findViewById(R.id.rvpackage);


        ArrayList<PojoModel> listpackage= new ArrayList<>();

        listpackage.add(new PojoModel(R.drawable.pac,"Packaging Services/پیکیجنگ خدمات"));



        PackageAdapter adapter= new PackageAdapter(listpackage);
        rvpackage.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        rvpackage.setLayoutManager(linearLayoutManager);

    }
}