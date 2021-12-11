package com.example.labourmanagementsystem.sub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.labourmanagementsystem.Adapters.BeautyAdapter;
import com.example.labourmanagementsystem.Adapters.LessonAdapter;
import com.example.labourmanagementsystem.Models.PojoModel;
import com.example.labourmanagementsystem.R;

import java.util.ArrayList;

public class BeautyActivity extends AppCompatActivity {

    RecyclerView rvbeauty;
    ArrayList<PojoModel> listbeauty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty);


        rvbeauty=findViewById(R.id.rvbeauty);


        ArrayList<PojoModel> listbeauty= new ArrayList<>();

        listbeauty.add(new PojoModel(R.drawable.mens,"Mens Saloon/مینس سیلون"));
        listbeauty.add(new PojoModel(R.drawable.womens,"Womens Saloon/خواتین سیلون"));
        listbeauty.add(new PojoModel(R.drawable.makeupartist,"Make Up Artist/آرٹسٹ قضاء"));

        BeautyAdapter adapter= new BeautyAdapter(listbeauty);
        rvbeauty.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        rvbeauty.setLayoutManager(linearLayoutManager);

    }
}