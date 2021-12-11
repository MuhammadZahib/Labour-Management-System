package com.example.labourmanagementsystem.SubRequests;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.labourmanagementsystem.R;
import com.example.labourmanagementsystem.SubClass.RequestActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class WomenRequestActivity extends AppCompatActivity {

    EditText cName,cAddress,cPhone,cPrice,cDescription;
    Button confirmBooking;
    String labourId,skill,userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_women_request);

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        userID=user.getUid();
        initViews();
        getRequiredData();

        confirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadToFirebase();
            }
        });


    }

    private void uploadToFirebase() {
        final HashMap<String,Object> map= new HashMap<>();
        //   map.put("UserID",userID);
        map.put("CustomerId",userID);
        map.put("Skill",skill);
        map.put("CustomerName",cName.getText().toString());
        map.put("CustomerAddress",cAddress.getText().toString());
        map.put("CustomerPhone",cPhone.getText().toString());
        map.put("CustomerPrice",cPrice.getText().toString());
        map.put("CustomerDescription",cDescription.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Request").child(labourId).push().setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(WomenRequestActivity.this,"Request is sent Please wait For further Process",Toast.LENGTH_LONG)
                                .show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(WomenRequestActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getRequiredData() {
        Intent intent= getIntent();
        labourId=intent.getStringExtra("Labour Id");
        skill=intent.getStringExtra("Skill");
    }

    private void initViews() {
        cName=findViewById(R.id.name_request);
        cAddress=findViewById(R.id.completeAddress_request);
        cPhone=findViewById(R.id.phone_request);
        cPrice=findViewById(R.id.priceEstimate_request);
        cDescription=findViewById(R.id.description_request);
        confirmBooking=findViewById(R.id.confirm_booking_request);
    }
}