package com.example.labourmanagementsystem.SubClass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.labourmanagementsystem.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class RequestActivity extends AppCompatActivity {

    EditText cName,cAddress,cPhone,cPrice,cDescription,date,time;
    Button confirmBooking;
    String labourId,skill,userID;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private Calendar cal;
    private int day;
    private int month;
    private int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        userID=user.getUid();
        initViews();
        getRequiredData();
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog();
            }
        });



        confirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(RequestActivity.this);
                builder1.setMessage("Note! You must have to pay 200 Rs to the labour who is visiting for the service either the work agreement is " +
                        "signed or not!");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Agree",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                uploadToFirebase();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });



    }

    private boolean validate(EditText e) {
        // check the lenght of the enter data in EditText and give error if its empty
        if (e.getText().toString().trim().length() > 0) {
            return true; // returs true if field is not empty
        }
        e.setError("Please Fill This");
        e.requestFocus();
        return false;
    }

    public void DateDialog(){

        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {

                date.setText(dayOfMonth+"/"+monthOfYear+"/"+year);

            }};

        DatePickerDialog dpDialog=new DatePickerDialog(this, listener, year, month, day);
        dpDialog.show();

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
        map.put("Date",date.getText().toString());
        map.put("Time",time.getText().toString());
        map.put("CustomerDescription",cDescription.getText().toString());

        if(validate(time) && validate(date) && validate(cName) && validate(cAddress) && validate(cPhone) && validate(cPrice) && validate(cDescription)){
        FirebaseDatabase.getInstance().getReference().child("Request").child(labourId).push().setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(RequestActivity.this,"Request is sent Please wait For further Process",Toast.LENGTH_LONG)
                                .show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RequestActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        });}
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
        date=findViewById(R.id.txt_date_request);
        time=findViewById(R.id.txt_time_request);
    }
}