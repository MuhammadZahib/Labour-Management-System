package com.example.labourmanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.labourmanagementsystem.Fragments.ProfileFragment;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class GigActivity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener{

    Spinner spn;
    String category[];
    String userID;
    String categ;
    Uri filepath;
    ProgressDialog progressDialog;
    ImageView Lprofile;
    EditText Cname,city,description,phone;
    Button createProfile;

    DatabaseReference DR;
    StorageReference SR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gig);

        getSupportActionBar().hide();
        progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Creating your Gig");
        progressDialog.setCanceledOnTouchOutside(false);

        Lprofile=findViewById(R.id.Lprofile);
        spn=findViewById(R.id.spinner);
        description=findViewById(R.id.description);
        Cname=findViewById(R.id.Cname);
        city=findViewById(R.id.city);
        createProfile=findViewById(R.id.createProfile);
        phone=findViewById(R.id.phone);

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        userID=user.getUid();



        ArrayAdapter<CharSequence> adapter=  ArrayAdapter.createFromResource(GigActivity.this,R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);
        spn.setOnItemSelectedListener(this);

        DR= FirebaseDatabase.getInstance().getReference();
        SR= FirebaseStorage.getInstance().getReference();

        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadtoFirebase();
            }
        });

        Lprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(GigActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        filepath=data.getData();
        Lprofile.setImageURI(filepath);
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void uploadtoFirebase() {
        progressDialog.show();
        FirebaseStorage FS= FirebaseStorage.getInstance();
        StorageReference RS=FS.getReference().child(categ).child(userID);
        RS.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                RS.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        final HashMap<String,Object> map= new HashMap<>();
                        map.put("UserID",userID);
                        map.put("Image",uri.toString());
                        map.put("CompanyName",Cname.getText().toString());
                        map.put("City",city.getText().toString());
                        map.put("Contact",phone.getText().toString());
                        map.put("Description",description.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Labours").child(categ).child(userID).setValue(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        progressDialog.dismiss();
                                        Toast.makeText(GigActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                                        finish();

                                    }
                                });

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(GigActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        categ= parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }






}