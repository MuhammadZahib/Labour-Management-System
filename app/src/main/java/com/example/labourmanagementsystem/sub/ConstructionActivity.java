package com.example.labourmanagementsystem.sub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.labourmanagementsystem.Adapters.ConstructionAdapter;
import com.example.labourmanagementsystem.Adapters.FittingAdapter;
import com.example.labourmanagementsystem.Models.PojoModel;
import com.example.labourmanagementsystem.R;

import java.util.ArrayList;

public class ConstructionActivity extends AppCompatActivity {

    RecyclerView rvconstruction;
    ArrayList<PojoModel> listconstruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction);

        rvconstruction=findViewById(R.id.rvconstruction);


        ArrayList<PojoModel> listconstruction= new ArrayList<>();

        listconstruction.add(new PojoModel(R.drawable.civil,"Civil Engineer/سول انجینئر"));
        listconstruction.add(new PojoModel(R.drawable.paint,"Painter/پینٹر"));


        ConstructionAdapter adapter= new ConstructionAdapter(listconstruction);
        rvconstruction.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        rvconstruction.setLayoutManager(linearLayoutManager);

    }
}