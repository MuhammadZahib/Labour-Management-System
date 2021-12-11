package com.example.labourmanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.labourmanagementsystem.Fragments.HomeFragment;
import com.example.labourmanagementsystem.Fragments.InboxFragment;
import com.example.labourmanagementsystem.Fragments.ProfileFragment;
import com.example.labourmanagementsystem.Fragments.TaskStatusFragment;
import com.example.labourmanagementsystem.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth auth;
    private ImageView btnSetting;
    private Boolean check;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

      //  btnSetting.findViewById(R.id.image_setting_main);

//        Intent intent= getIntent();
//      check=  intent.getBooleanExtra("check",false);
//      if(check){
//          finish();
//      }

        binding.imageSettingMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SettingActivity.class));
            }
        });

        binding.imageProfileMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SideActivity.class);
                startActivityForResult(intent,200);
              //  startActivity(new Intent(MainActivity.this,SideActivity.class));
            }
        });

        getSupportActionBar().hide();
        auth=FirebaseAuth.getInstance();


        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,new HomeFragment());
        transaction.commit();

        binding.bottomnavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();


                switch (item.getItemId()){

                    case R.id.home:

                        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
                        break;

                    case R.id.task_status:
                        transaction.replace(R.id.container,new TaskStatusFragment());
                        break;

                    case R.id.inbox:
                        transaction.replace(R.id.container,new InboxFragment());
                        break;

                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,new ProfileFragment()).commit();
                        break;
                }
                transaction.commit();

                return true;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==200){
            if(resultCode==100){
                assert data != null;
                check= data.getBooleanExtra("check",false);
                if(check){
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    finish();
                }
            }
        }
    }

    //    private void checkUser() {
//        // check if user is not logged in then moved to the logged in activity.
//        FirebaseUser user = auth.getCurrentUser();
//        if(user==null){
//            //user not logged in
//            startActivity(new Intent(this,LoginActivity.class));
//            finish();
//
//        }
//        else {
//            // user logged in
//            String email=user.getEmail();
//            // set to main email
//          //  binding.mainEmail.setText(email);
//        }
//    }


////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        getMenuInflater().inflate(R.menu.main_toolbar_menu,menu);
////        return true;
////    }
////
////    @Override
////    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
////
////        switch (item.getItemId()){
////
////            case R.id.about:
////                Toast.makeText(this, "User Account", Toast.LENGTH_SHORT).show();
////                break;
////
////            case R.id.settings:
////                startActivity(new Intent(MainActivity.this,SettingActivity.class));
////                Toast.makeText(this, "Settings toast", Toast.LENGTH_SHORT).show();
////                break;
//
//
//
//
//        }
//        return true ;
//    }
}