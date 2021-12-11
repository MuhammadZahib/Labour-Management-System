package com.example.labourmanagementsystem.sub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.labourmanagementsystem.Adapters.ConstructionAdapter;
import com.example.labourmanagementsystem.Adapters.EngineerAdapter;
import com.example.labourmanagementsystem.Models.PojoModel;
import com.example.labourmanagementsystem.R;

import java.util.ArrayList;

public class EngineerActivity extends AppCompatActivity {
    RecyclerView rvengineer;
    ArrayList<PojoModel> listengineer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engineer);

        rvengineer=findViewById(R.id.rvengineer);


        ArrayList<PojoModel> listengineer= new ArrayList<>();

        listengineer.add(new PojoModel(R.drawable.electric,"Electrician/الیکٹریشن"));
        listengineer.add(new PojoModel(R.drawable.ac,"Split AC Services/اسپلٹ اے سی سروس"));
        listengineer.add(new PojoModel(R.drawable.mechanic,"Mechanic/مکینک"));
        listengineer.add(new PojoModel(R.drawable.plumber,"Plumber/پلمبر"));


        EngineerAdapter adapter= new EngineerAdapter(listengineer);
        rvengineer.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        rvengineer.setLayoutManager(linearLayoutManager);

    }
}