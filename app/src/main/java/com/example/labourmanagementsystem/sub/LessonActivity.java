package com.example.labourmanagementsystem.sub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.labourmanagementsystem.Adapters.DressAdapter;
import com.example.labourmanagementsystem.Adapters.LessonAdapter;
import com.example.labourmanagementsystem.Models.PojoModel;
import com.example.labourmanagementsystem.R;

import java.util.ArrayList;

public class LessonActivity extends AppCompatActivity {

    RecyclerView rvles;
    ArrayList<PojoModel> listlesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        rvles=findViewById(R.id.rvlesson);


        ArrayList<PojoModel> listlesson= new ArrayList<>();

        listlesson.add(new PojoModel(R.drawable.cooking,"Cooking Tutor/کھانا پکانے ٹیوٹر"));
        listlesson.add(new PojoModel(R.drawable.tutor,"Home Tutor/ہوم ٹیوٹر"));

        LessonAdapter adapter= new LessonAdapter(listlesson);
        rvles.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        rvles.setLayoutManager(linearLayoutManager);


    }
}