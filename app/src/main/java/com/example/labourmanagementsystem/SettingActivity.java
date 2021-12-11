package com.example.labourmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.labourmanagementsystem.Setting.AboutActivity;
import com.example.labourmanagementsystem.Setting.FeedbackActivity;
import com.example.labourmanagementsystem.Setting.PrivacyActivity;

public class SettingActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        String[] cities={"About App","Privacy Policy","feedback Us"};

        listView=findViewById(R.id.settinglist);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(SettingActivity.this,android.R.layout.simple_dropdown_item_1line,cities);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  Toast.makeText(SettingActivity.this, "Beautiful "+ cities[position], Toast.LENGTH_SHORT).show();

                switch(position){
                    case 0:
                        startActivity(new Intent(SettingActivity.this, AboutActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(SettingActivity.this, PrivacyActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(SettingActivity.this, FeedbackActivity.class));
                        break;

                    default:
                        break;

                }

            }
        });


    }
}