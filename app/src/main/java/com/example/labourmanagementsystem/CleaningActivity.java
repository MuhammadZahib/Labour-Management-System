package com.example.labourmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.labourmanagementsystem.Adapters.CleaningAdapter;
import com.example.labourmanagementsystem.Adapters.RVAdapter;
import com.example.labourmanagementsystem.Models.PojoModel;

import java.util.ArrayList;

public class CleaningActivity extends AppCompatActivity {

    RecyclerView rvsub;
    ArrayList<PojoModel> listsub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning);
       // getSupportActionBar().hide();

        rvsub=findViewById(R.id.sub);


        ArrayList<PojoModel> listsub= new ArrayList<>();

        listsub.add(new PojoModel(R.drawable.carfur,"Carpet/Furniture/قالین / فرنیچر کی صفائی"));
        listsub.add(new PojoModel(R.drawable.cleaning,"Domestic Cleaning/گھریلو صفائی"));
        listsub.add(new PojoModel(R.drawable.car_wash,"Car Wash/کار کی دھلائی"));
        listsub.add(new PojoModel(R.drawable.dry,"Dry Cleaning/ڈرائی کلینگ"));

        CleaningAdapter adapter= new CleaningAdapter(listsub);
        rvsub.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        rvsub.setLayoutManager(linearLayoutManager);




    }
}