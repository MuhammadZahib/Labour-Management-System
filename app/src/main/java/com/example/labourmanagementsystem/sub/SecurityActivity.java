package com.example.labourmanagementsystem.sub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.labourmanagementsystem.Adapters.LessonAdapter;
import com.example.labourmanagementsystem.Adapters.SecurityAdapter;
import com.example.labourmanagementsystem.Models.PojoModel;
import com.example.labourmanagementsystem.R;

import java.util.ArrayList;

public class SecurityActivity extends AppCompatActivity {

    RecyclerView rvsecurity;
    ArrayList<PojoModel> listsecurity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        rvsecurity=findViewById(R.id.rvsecurity);


        ArrayList<PojoModel> listsecurity= new ArrayList<>();

        listsecurity.add(new PojoModel(R.drawable.guard,"Security Guard/چوکیدار"));
        listsecurity.add(new PojoModel(R.drawable.cctv,"CCTV provider"));

        SecurityAdapter adapter= new SecurityAdapter(listsecurity);
        rvsecurity.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        rvsecurity.setLayoutManager(linearLayoutManager);




    }
}